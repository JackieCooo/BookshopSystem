package com.bookshop.backend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bookshop.backend.utility.BookUtil;
import com.bookshop.backend.utility.RequestUtil;
import com.bookshop.backend.data.Book;
import com.bookshop.backend.jsonconvert.JsonResult;
import com.bookshop.backend.mapper.BookMapper;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BookController {
    
    @Autowired
    private BookMapper bookMapper;

    @GetMapping("/book/{id}")
    public JsonResult<Book> sendBookInfo(@PathVariable Integer id) {
        Book book;
        if ((book = getBookInfo(id)) != null) {
            return new JsonResult<>(book);
        }
        else {
            return new JsonResult<>("1", "请求失败");
        }
    }

    @GetMapping("/book/pic/{id}")
    public JsonResult<String> sendBookCover(@PathVariable Integer id) {
        return new JsonResult<>();
    }

    public Book getBookInfo(Integer id) {
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        wrapper.select("id").eq("id", id);
        Long cnt = bookMapper.selectCount(wrapper);

        // 数据库中没有对应的书，爬取
        if (cnt == 0) {
            String url = "https://book.douban.com/subject/" + id.toString();

            Document doc = RequestUtil.requestHtml(url);

            if (doc != null) {
                Book info = new Book();
                info.setId(id);
                BookUtil.extractBookInfo(doc, info);
                storeBook(info);
            }

            return null;

        }

        wrapper.clear();
        wrapper.eq("id", id);

        return bookMapper.selectOne(wrapper);
    }

    private void storeBook(Book book) {
        bookMapper.insert(book);
    }

}

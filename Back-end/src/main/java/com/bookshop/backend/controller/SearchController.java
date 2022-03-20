package com.bookshop.backend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bookshop.backend.data.Book;
import com.bookshop.backend.jsonconvert.JsonResult;
import com.bookshop.backend.mapper.BookMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SearchController {

    @Resource
    private BookMapper bookMapper;

    @GetMapping("/search")
    public JsonResult<List<Book>> sendSearchResult(@RequestParam String text) {
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        wrapper.select("id", "name", "author", "date", "publisher", "price").like("name", text);
        List<Book> books = bookMapper.selectList(wrapper);
        return new JsonResult<>(books);
    }
}

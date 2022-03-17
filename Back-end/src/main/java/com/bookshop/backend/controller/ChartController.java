package com.bookshop.backend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bookshop.backend.mapper.BookMapper;
import com.bookshop.backend.utility.BookUtil;
import com.bookshop.backend.utility.RequestUtil;
import com.bookshop.backend.data.Book;
import com.bookshop.backend.data.ChartBoardInfo;
import com.bookshop.backend.jsonconvert.JsonResult;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api")
public class ChartController {

    @Autowired
    private BookMapper bookMapper;

    @RequestMapping("/chart/{type}")
    public JsonResult<ArrayList<Book>> getInfo(@PathVariable String type, @RequestParam(required = false) Integer detail) {

        Document doc;

        if (type.equals("new")) doc = RequestUtil.requestHtml("https://book.douban.com/latest?subcat=%E5%85%A8%E9%83%A8");
        else doc = RequestUtil.requestHtml("https://book.douban.com/chart?subcat=" + type);

        if (doc != null) {
            ArrayList<Book> info = new ArrayList<>();
            extractChartInfo(doc, info, detail);
            return new JsonResult<>(info);
        }
        return new JsonResult<>("1", "请求失败");
    }

    /**
     * 从HTML文档中提取指定的榜单信息
     * @param doc HTML文档
     * @param info 榜单信息
     * @param detail 返回信息的详细程度 1：榜单详情页 2：详细榜单面板 3：简介榜单面板 4：只显示名字的榜单
     */
    private void extractChartInfo(Document doc, ArrayList<Book> info, Integer detail) {
        Element element;
        Elements elements;
        elements = doc.select("li.media");
        boolean first = true;

        // 获取每一本书的信息
        for (Element i : elements) {

            // 获取书号
            Integer id;
            element = i.select("div.media__body > h2 > a").first();
            assert element != null;
            String tmp = element.attr("href");
            Pattern idPattern = Pattern.compile("\\d{8}");
            Matcher matcher = idPattern.matcher(tmp);
            if (matcher.find()) {
                id = Integer.parseInt(matcher.group(0));
                System.out.println("id: " + matcher.group(0));
            }
            else {
                System.out.println("请求失败");
                return;
            }

            // 是否有电子书
            boolean hasEBook = false;
            element = i.select("div.ebook-link").first();
            if (element != null) {
                hasEBook = true;
                System.out.println("hasEBook: Yes");
            }
            else {
                System.out.println("hasEBook: No");
            }

            // 查看数据库是否有这本书
            QueryWrapper<Book> wrapper = new QueryWrapper<>();
            wrapper.select("id").eq("id", id);
            Long cnt = bookMapper.selectCount(wrapper);

            // 数据库中没有对应的书，爬取
            Book book = new Book(id);
            if (cnt == 0) {
                String url = "https://book.douban.com/subject/" + id;

                doc = RequestUtil.requestHtml(url);

                if (doc != null) {
                    BookUtil.extractBookInfo(doc, book);
                    book.setHasEBook(hasEBook);
                    bookMapper.insert(book);  // 存到数据库
                }
                else {
                    System.out.println("爬虫失败");
                    return;
                }

            }

            // 从数据库获取信息
            wrapper.clear();
            wrapper.select("id", "author", "name", "date", "publisher", "price", "has_e_book", "has_secondhand_book", "pic").eq("id", id);
            Book res = bookMapper.selectOne(wrapper);
            if (element != null) {  // 更新是否有电子书
                res.setHasEBook(true);
                bookMapper.updateById(res);
            }

            info.add(res);

        }
    }
}

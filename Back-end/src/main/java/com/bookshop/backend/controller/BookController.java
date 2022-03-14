package com.bookshop.backend.controller;

import com.bookshop.backend.RequestUtil;
import com.bookshop.backend.data.BookInfo;
import com.bookshop.backend.jsonconvert.JsonResult;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api")
public class BookController {

    @GetMapping("/book/{id}")
    public JsonResult<BookInfo> getBookInfo(@PathVariable Integer id) {
        String url = "https://book.douban.com/subject/" + id.toString();

        Document doc = RequestUtil.requestHtml(url);

        if (doc != null) {
            BookInfo info = new BookInfo();
            info.setId(id);
            extractBookInfo(doc, info);
            return new JsonResult<>(info);
        }
        return new JsonResult<>("1", "请求失败");
    }

    @GetMapping("/book/pic/{id}")
    public JsonResult<String> getBookCover(@PathVariable Integer id) {
        return new JsonResult<>();
    }

    private void storeBookCover(Integer id) {

    }

    private void extractBookInfo (Document doc, BookInfo info) {
        Element element = null;

        element = doc.select("a.nbg > img").first();
        assert element != null;
        // 获取封面图id
        String picUrl = element.attr("src");
        Pattern picPattern = Pattern.compile("\\d{8}");
        Matcher matcher = picPattern.matcher(picUrl);
        if (matcher.find()) {
            info.setPic(matcher.group(0));
            System.out.println("pic: " + matcher.group(0));
        }

        // 获取书名
        info.setName(element.attr("alt"));
        System.out.println("title: " + element.attr("alt"));

        // 获取作者名
        element = doc.select("div#info").first();
        assert element != null;
        String infoText = element.text();
        Pattern authorPattern = Pattern.compile("作者\s*:\s(.*?)\s*出版社");
        matcher = authorPattern.matcher(infoText);
        if (matcher.find()) {
            info.setAuthor(matcher.group(1));
            System.out.println("author: " + matcher.group(1));
        }

        // 获取出版社名
        Pattern publisherPattern = Pattern.compile("出版社\s*:\s(.*?)\s*(出品方|原作名|译者|出版年)");
        matcher = publisherPattern.matcher(infoText);
        if (matcher.find()) {
            info.setPublisher(matcher.group(1));
            System.out.println("publisher: " + matcher.group(1));
        }

        // 获取出版时间
        Pattern datePattern = Pattern.compile("出版年\s*:\s(.*?)\s*页数");
        matcher = datePattern.matcher(infoText);
        if (matcher.find()) {
            info.setDate(matcher.group(1));
            System.out.println("date: " + matcher.group(1));
        }

        // 获取价格
        Pattern pricePattern = Pattern.compile("定价\s*:\s(.*?)\s*元");
        matcher = pricePattern.matcher(infoText);
        if (matcher.find()) {
            info.setPrice(Double.valueOf(matcher.group(1)));
            System.out.println("price: " + matcher.group(1));
        }

        // 获取ISBN码
        Pattern isbnPattern = Pattern.compile("\\d{13}?");
        matcher = isbnPattern.matcher(infoText);
        if (matcher.find()) {
            info.setIsbn(matcher.group(0));
            System.out.println("ISBN: " + matcher.group(0));
        }

        // 获取图书简介
        element = doc.select("span.all > div > div.intro").first();
        assert element != null;
        info.setBookIntroduction(element.text());
        System.out.println("book introduction: ");
        System.out.println(element.text());

        // 获取作者简介
        element = doc.select("span.all > div.intro").first();
        assert element != null;
        info.setAuthorIntroduction(element.text());
        System.out.println("author introduction: ");
        System.out.println(element.text());

        // 获取目录
        String cssPattern = "div#dir_" + info.getId() + "_full";
//        System.out.println(cssPattern);
        element = doc.select(cssPattern).first();
        assert element != null;
        info.setDirectory(element.text());
        System.out.println("directory: ");
        System.out.println(element.text());
    }

}

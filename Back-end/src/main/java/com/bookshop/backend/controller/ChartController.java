package com.bookshop.backend.controller;

import com.bookshop.backend.RequestUtil;
import com.bookshop.backend.data.BookInfo;
import com.bookshop.backend.data.ChartInfo;
import com.bookshop.backend.data.ChartBoardInfo;
import com.bookshop.backend.jsonconvert.JsonResult;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api")
public class ChartController {

    @RequestMapping("/chart/{type}")
    public JsonResult<ChartInfo> getInfo(@PathVariable String type) {

        Document doc;

        if (type.equals("new")) doc = RequestUtil.requestHtml("https://book.douban.com/latest?subcat=%E5%85%A8%E9%83%A8");
        else doc = RequestUtil.requestHtml("https://book.douban.com/chart?subcat=" + type);

        if (doc != null) {
            ChartInfo info = new ChartInfo();
            extractChartInfo(doc, info);
            return new JsonResult<>(info);
        }
        return new JsonResult<>("1", "请求失败");
    }

    @GetMapping("/board/{type}")
    public JsonResult<ChartBoardInfo> getChartBoardInfo(@PathVariable String type, @RequestParam(required = false) Integer onlyNames) {
        Document doc = RequestUtil.requestHtml("https://book.douban.com/chart?subcat=" + type);
        if (doc != null) {
            ChartBoardInfo info = new ChartBoardInfo();
            return new JsonResult<>(info);
        }
        return new JsonResult<>("1", "请求失败");
    }

    private void extractChartInfo(Document doc, ChartInfo info) {
        Element element = null;
        Elements elements = null;
        elements = doc.select("li.media");

        for (Element i : elements) {

            BookInfo bookInfo = new BookInfo();

            // 获取封面图
            element = i.select("div.media__img > a > img").first();
            assert element != null;
            bookInfo.setPic(element.attr("src"));
            System.out.println("pic: " + element.attr("src"));

            // 获取书名
            element = i.select("div.media__body > h2 > a").first();
            assert element != null;
            bookInfo.setName(element.text());
            System.out.println("name: " + element.text());

            // 获取书号
            String tmp = element.attr("href");
            Pattern idPattern = Pattern.compile("\\d{8}?");
            Matcher matcher = idPattern.matcher(tmp);
            if (matcher.find()) {
                bookInfo.setId(Integer.parseInt(matcher.group(0)));
                System.out.println("id: " + matcher.group(0));
            }

            // 获取作者
            element = i.select("div.media__body > p.subject-abstract").first();
            assert element != null;
            tmp = element.text();
            Pattern authorPattern = Pattern.compile("(.*?)\s/\s\\d+?");
            matcher = authorPattern.matcher(tmp);
            if (matcher.find()) {
                bookInfo.setAuthor(matcher.group(1));
                System.out.println("author: " + matcher.group(1));
            }

            // 获取出版日期
            Pattern datePattern = Pattern.compile("\\d{4}-\\d{1,2}");
            matcher = datePattern.matcher(tmp);
            if (matcher.find()) {
                bookInfo.setDate(matcher.group(0));
                System.out.println("date: " + matcher.group(0));
            }

            // 获取出版社名
            Pattern publisherPattern = Pattern.compile("\\d+?\s/\s(.*?)\s/\s\\d+?");
            matcher = publisherPattern.matcher(tmp);
            if (matcher.find()) {
                bookInfo.setPublisher(matcher.group(1));
                System.out.println("publisher: " + matcher.group(1));
            }

            // 获取价格
            Pattern pricePattern = Pattern.compile("/\s(\\d{1,3}.\\d{1,3}|\\d{1,3})元*\s/");
            matcher = pricePattern.matcher(tmp);
            if (matcher.find()) {
                bookInfo.setPrice(Double.valueOf(matcher.group(1)));
                System.out.println("price: " + matcher.group(1));
            }

            // 是否有电子书
            element = i.select("div.ebook-link").first();
            if (element != null) {
                bookInfo.setHasEBook(true);
                System.out.println("hasEBook: Yes");
            }
            else {
                System.out.println("hasEBook: No");
            }

            info.getChart().add(bookInfo);

        }
    }
}

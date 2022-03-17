package com.bookshop.backend.controller;

import com.bookshop.backend.utility.RequestUtil;
import com.bookshop.backend.data.Review;
import com.bookshop.backend.data.ReviewListInfo;
import com.bookshop.backend.jsonconvert.JsonResult;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api")
public class ReviewController {

    @GetMapping("/rev/list")
    public JsonResult<ReviewListInfo> getReviewChartList() {
        String url = "https://book.douban.com/review/best/";
        Document doc = RequestUtil.requestHtml(url);
        if (doc != null) {
            ReviewListInfo info = new ReviewListInfo();
            extractReviewListInfo(doc, info);
            return new JsonResult<>(info);
        }

        return new JsonResult<>("1", "请求失败");
    }

    @GetMapping("/rev/{id}")
    public JsonResult<String> getReview(@PathVariable Integer id) {
        String url = "https://book.douban.com/review/" + id.toString();
        Document doc = RequestUtil.requestHtml(url);
        if (doc != null) {
            Elements elements = doc.select("div.review-content > p, div.review-content > div > div > img");
            System.out.println(elements.html());
            return new JsonResult<>(elements.html());
        }

        return new JsonResult<>("1", "请求失败");
    }

    private void extractReviewInfo(Document doc, Review info) {
        Elements elements = doc.select("div.review-content > p, div.review-content > div > div > img");
        System.out.println(elements.html());
    }

    private void extractReviewListInfo(Document doc, ReviewListInfo info) {
        Elements elements = doc.select("div.review-item");
        for (Element i : elements) {
            Element element = null;
            Review review = new Review();

            // 获取书评号
            review.setId(Integer.parseInt(i.attr("id")));
            System.out.println("id: " + i.attr("id"));

            // 获取书号
            element = i.select("a.subject-img").first();
            assert element != null;
            String tmp = element.attr("href");
            Pattern bookPattern = Pattern.compile("\\d{8}");
            Matcher matcher = bookPattern.matcher(tmp);
            if (matcher.find()) {
                review.setBookId(Integer.parseInt(matcher.group(0)));
                System.out.println("book id: " + matcher.group(0));
            }

            // 获取题目
            element = i.select("div.main-bd > h2 > a").first();
            assert element != null;
            review.setTitle(element.text());
            System.out.println("title: " + element.text());

            // 获取作者名
            element = i.select("header.main-hd > a.name").first();
            assert element != null;
            review.setAuthor(element.text());
            System.out.println("author: " + element.text());

            // 获取发布时间
            element = i.select("header.main-hd > span.main-meta").first();
            assert element != null;
            review.setDate(element.text());
            System.out.println("date: " + element.text());

            // 获取内容
            element = i.select("div.short-content").first();
            assert element != null;
            review.setContent(element.text());
            System.out.println("content: " + element.text());

            info.getList().add(review);
        }
    }

}

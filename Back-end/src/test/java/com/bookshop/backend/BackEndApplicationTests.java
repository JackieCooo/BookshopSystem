package com.bookshop.backend;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bookshop.backend.data.*;
import com.bookshop.backend.mapper.*;
import com.bookshop.backend.utility.BookUtil;
import com.bookshop.backend.utility.RequestUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
class BackEndApplicationTests {

    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private ChartTypeMapper chartTypeMapper;
    @Autowired
    private ChartMapper chartMapper;
    @Autowired
    private ReviewChartMapper reviewChartMapper;
    @Autowired
    private ReviewMapper reviewMapper;

    private final String[] types = {"all", "literary", "history", "social", "novel", "tech", "art", "new"};

    @Test
    void updateData() {

        if (false) {
            // 更新榜单信息
            for (String i : types) {
                System.out.println("更新" + i + "榜单");

                Document doc;

                if (i.equals("new"))
                    doc = RequestUtil.requestHtml("https://book.douban.com/latest?tag=%E5%85%A8%E9%83%A8");
                else doc = RequestUtil.requestHtml("https://book.douban.com/chart?subcat=" + i);

                if (doc != null) {
                    QueryWrapper<ChartType> wrapper = new QueryWrapper<>();
                    wrapper.eq("name", i);
                    ChartType res = chartTypeMapper.selectOne(wrapper);
                    extractChart(doc, res.getId());
                } else {
                    System.out.println(i + "请求失败");
                }

            }
        }

        // 更新书评信息
        System.out.println("更新书评");

        Document doc = RequestUtil.requestHtml("https://book.douban.com/review/best/");
        if (doc != null) {
            extractReviewChart(doc);
        }

    }

    private void extractReviewChart(Document doc) {
        Elements elements = doc.select("div.review-item");
        int rank = 1;
        for (Element i : elements) {
            Element element;
            Review review = new Review();

            // 获取书评号
            review.setId(Integer.parseInt(i.attr("id")));
            System.out.println("id: " + i.attr("id"));

            // 查看数据库是否有这篇书评，有的话跳过
            QueryWrapper<ReviewChart> reviewChartWrapper = new QueryWrapper<>();
            reviewChartWrapper.eq("review_id", review.getId());
            Long exist = reviewChartMapper.selectCount(reviewChartWrapper);
            if (exist != 0) continue;

            // 获取书号
            element = i.select("a.subject-img").first();
            assert element != null;
            String tmp = element.attr("href");
            Pattern bookPattern = Pattern.compile("\\d{1,8}");
            Matcher matcher = bookPattern.matcher(tmp);
            if (matcher.find()) {
                review.setBookId(Integer.parseInt(matcher.group(0)));
                System.out.println("book id: " + matcher.group(0));

                // 查看是否有这本书
                QueryWrapper<Book> bookWrapper = new QueryWrapper<>();
                bookWrapper.select("id").eq("id", review.getBookId());
                Long cnt = bookMapper.selectCount(bookWrapper);

                // 没有这本书，爬取
                if (cnt == 0) {
                    Book book = new Book(review.getBookId());

                    String url = "https://book.douban.com/subject/" + book.getId();

                    doc = RequestUtil.requestHtml(url);

                    if (doc != null) {
                        BookUtil.extractBookInfo(doc, book);
                        bookMapper.insert(book);  // 存到数据库
                    }
                    else {
                        System.out.println("爬虫失败");
                        return;
                    }
                }
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

            // 获取书评简介
            element = i.select("div.short-content").first();
            assert element != null;
            review.setIntro(element.text());
            System.out.println("intro: " + element.text());

            // 获取书评内容
            String url = "https://book.douban.com/review/" + review.getId();
            doc = RequestUtil.requestHtml(url);
            if (doc != null) {
                extractReviewContent(doc, review);
            }

            // 存进数据库
            reviewMapper.insert(review);

            reviewChartMapper.insert(new ReviewChart(review.getId(), rank));

            ++rank;
        }
    }

    private void extractReviewContent(Document doc, Review info) {
        Elements elements = doc.select("div.review-content > p");
        StringBuilder tmp = new StringBuilder();
        for (Element i : elements) {
            tmp.append(i.text());
            tmp.append('\n');
        }
        String content = tmp.toString();
        info.setContent(content);
    }

    private void extractChart(Document doc, Integer typeId) {
        Element element;
        Elements elements;
        elements = doc.select("li.media");

        // 获取每一本书的信息
        Integer rank = 1;
        for (Element i : elements) {

            // 获取书号
            Integer id;
            element = i.select("div.media__body > h2 > a").first();
            assert element != null;
            String tmp = element.attr("href");
            Pattern idPattern = Pattern.compile("\\d{1,8}");
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

            // 向数据库添加榜单信息
            QueryWrapper<Chart> chartWrapper = new QueryWrapper<>();
            chartWrapper.eq("type_id", typeId).eq("book_id", id);
            cnt = chartMapper.selectCount(chartWrapper);
            if (cnt == 0) {
                chartMapper.insert(new Chart(typeId, id, rank));
            }

            ++rank;
        }
    }

}

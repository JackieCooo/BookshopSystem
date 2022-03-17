package com.bookshop.backend;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bookshop.backend.data.Book;
import com.bookshop.backend.mapper.BookMapper;
import com.bookshop.backend.mapper.ChartTypeMapper;
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

    private final String[] types = {"all", "literary", "history", "social", "novel", "tech", "art", "new"};

    @Test
    void contextLoads() {

        for (String i : types) {
            Document doc;

            if (i.equals("new")) doc = RequestUtil.requestHtml("https://book.douban.com/latest?subcat=%E5%85%A8%E9%83%A8");
            else doc = RequestUtil.requestHtml("https://book.douban.com/chart?subcat=" + i);

            if (doc != null) {
                extractInfo(doc);
            }
            else {
                System.out.println(i + "请求失败");
            }

        }

    }

    private void extractInfo(Document doc) {
        Element element;
        Elements elements;
        elements = doc.select("li.media");

        // 获取每一本书的信息
        int rank = 1;
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

            ++rank;
        }
    }

}

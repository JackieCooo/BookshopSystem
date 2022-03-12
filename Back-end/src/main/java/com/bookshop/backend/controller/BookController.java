package com.bookshop.backend.controller;

import com.bookshop.backend.data.BookInfo;
import com.bookshop.backend.jsonconvert.JsonResult;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api")
public class BookController {

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.51 Safari/537.36";

    @GetMapping("/book/{id}")
    public JsonResult<BookInfo> getBookInfo(@PathVariable Integer id) {
        String url = "https://book.douban.com/subject/" + id.toString();
        System.out.println(url);

        // 1.生成httpclient，相当于该打开一个浏览器
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();
        CloseableHttpResponse response = null;
        Document html = null;
        BookInfo info = new BookInfo(id);

        // 2.创建get请求，相当于在浏览器地址栏输入 网址
        HttpGet request = new HttpGet(url);
        try {
            request.setHeader("User-Agent", USER_AGENT);
            request.setConfig(requestConfig);

            // 3.执行get请求，相当于在输入地址栏后敲回车键
            response = httpClient.execute(request);

            // 4.判断响应状态为200，进行处理
            if (response.getStatusLine().getStatusCode() == HttpStatus.OK.value()) {
                System.out.println("请求成功");

                // 5.获取响应内容
                HttpEntity httpEntity = response.getEntity();
                String tmp = EntityUtils.toString(httpEntity, "GBK");
                html = Jsoup.parse(tmp);
                extractBookInfo(html, info);  // 爬虫提取信息
                return new JsonResult<>(info);
            }
            else {
                // 如果返回状态不是200，比如404（页面不存在）等，根据情况做处理，这里略
                System.out.println("请求失败");
                System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            // 6.关闭
            HttpClientUtils.closeQuietly(response);
            HttpClientUtils.closeQuietly(httpClient);
        }
        return new JsonResult<>();
    }

    private void extractBookInfo (Document doc, BookInfo info) {
        Element element = null;

        element = doc.select("a.nbg > img").first();
        assert element != null;
        // 获取封面图
        info.setPic(element.attr("src"));
        System.out.println("pic: " + element.attr("src"));

        // 获取书名
        info.setName(element.attr("alt"));
        System.out.println("title: " + element.attr("alt"));

        // 获取作者名
        element = doc.select("div#info").first();
        assert element != null;
        String infoText = element.text();
        Pattern authorPattern = Pattern.compile("作者\s*:\s(.*?)\s*出版社");
        Matcher matcher = authorPattern.matcher(infoText);
        if (matcher.find()) {
            info.setAuthor(matcher.group(1));
            System.out.println("author: " + matcher.group(1));
        }

        // 获取出版社名
        Pattern publisherPattern = Pattern.compile("出版社\s*:\s(.*?)\s*出品方");
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
            System.out.println("price" + matcher.group(1));
        }

        // 获取ISBN码
        Pattern isbnPattern = Pattern.compile("\\d{13}?");
        matcher = isbnPattern.matcher(infoText);
        if (matcher.find()) {
            info.setIsbn(matcher.group(0));
            System.out.println("ISBN: " + matcher.group(0));
        }

        // 获取图书简介
        element = doc.select("span > div > div.intro").first();
        assert element != null;
        info.setBookIntroduction(element.text());
        System.out.println("book introduction: ");
        System.out.println(element.text());

        // 获取作者简介
        element = doc.select("div.indent > div > div.intro").first();
        assert element != null;
        info.setAuthorIntroduction(element.text());
        System.out.println("author introduction: ");
        System.out.println(element.text());

        // 获取目录
        String cssPattern = "div#dir_" + info.getId() + "_full";
        element = doc.select(cssPattern).first();
        assert element != null;
        info.setDirectory(element.text());
        System.out.println("directory: ");
        System.out.println(element.text());


    }

}

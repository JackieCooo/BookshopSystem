package com.bookshop.backend.controller;

import com.bookshop.backend.data.BookBriefInfo;
import com.bookshop.backend.data.ChartInfo;
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
import org.jsoup.select.Elements;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api")
public class ChartController {

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.51 Safari/537.36";

    @RequestMapping("/chart/{type}")
    public JsonResult<ChartInfo> getInfo(@PathVariable String type, @RequestParam Integer detailed) {
        String url = "https://book.douban.com/chart?subcat=" + type;
        System.out.println(url);

        // 1.生成httpclient，相当于该打开一个浏览器
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();
        CloseableHttpResponse response = null;
        Document html = null;
        ChartInfo info = new ChartInfo();

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
                if (detailed == 1) {
                    extractDetailedChartInfo(html, info);
                }
                else {
                    extractBriefChartInfo(html, info);
                }
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

        JsonResult<ChartInfo> err = new JsonResult<>(null);
        err.setCode("1");
        err.setMsg("请求失败");
        return err;
    }

    private void extractDetailedChartInfo(Document doc, ChartInfo info) {
        Element element = null;
        Elements elements = null;
        elements = doc.select("li.media");
        boolean first = true;
        for (Element i : elements) {

            if (first) {
                // 获取封面图
                element = i.select("div.media__img > a > img").first();
                assert element != null;
                info.setCoverUrl(element.attr("src"));
                System.out.println("pic: " + element.attr("src"));
            }

            BookBriefInfo briefInfo = new BookBriefInfo();

            // 获取书名
            element = i.select("div.media__body > h2 > a").first();
            assert element != null;
            briefInfo.setName(element.text());
            System.out.println("name: " + element.text());

            // 获取书号
            String tmp = element.attr("href");
            Pattern idPattern = Pattern.compile("\\d{8}?");
            Matcher matcher = idPattern.matcher(tmp);
            if (matcher.find()) {
                briefInfo.setId(Integer.parseInt(matcher.group(0)));
                System.out.println("id: " + matcher.group(0));
            }

            // 获取作者
            element = i.select("div.media__body > p.subject-abstract").first();
            assert element != null;
            tmp = element.text();
            Pattern authorPattern = Pattern.compile("(.*?)\s/\s\\d+?");
            matcher = authorPattern.matcher(tmp);
            if (matcher.find()) {
                briefInfo.setAuthor(matcher.group(1));
                System.out.println("author: " + matcher.group(1));
            }

            info.getChart().add(briefInfo);

            if (first) {

                // 获取出版日期
                Pattern datePattern = Pattern.compile("\\d{4}-\\d{1,2}");
                matcher = datePattern.matcher(tmp);
                if (matcher.find()) {
                    info.setDate(matcher.group(0));
                    System.out.println("date: " + matcher.group(0));
                }

                // 获取出版社名
                Pattern publisherPattern = Pattern.compile("\\d+?\s/\s(.*?)\s/\s\\d+?");
                matcher = publisherPattern.matcher(tmp);
                if (matcher.find()) {
                    info.setPublisher(matcher.group(1));
                    System.out.println("publisher: " + matcher.group(1));
                }

                // 获取价格
                Pattern pricePattern = Pattern.compile("/\s(\\d{1,3}.\\d{1,3}|\\d{1,3})元*\s/");
                matcher = pricePattern.matcher(tmp);
                if (matcher.find()) {
                    info.setPrice(Double.valueOf(matcher.group(1)));
                    System.out.println("price: " + matcher.group(1));
                }

                // 是否有电子书
                element = i.select("div.ebook-link").first();
                if (element != null) {
                    info.setHasEBook(true);
                    System.out.println("hasEBook: Yes");
                }
                else {
                    System.out.println("hasEBook: No");
                }

                first = false;
            }
        }
    }

    private void extractBriefChartInfo(Document doc, ChartInfo info) {
        Element element = doc.select("li.media").first();
        assert element != null;

        // 获取封面图
        element = element.select("div.media__img > a > img").first();
        assert element != null;
        System.out.println("pic: " + element.attr("src"));

    }
}

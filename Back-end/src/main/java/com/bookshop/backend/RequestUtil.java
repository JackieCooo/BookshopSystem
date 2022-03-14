package com.bookshop.backend;

import com.bookshop.backend.data.ChartInfo;
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
import org.springframework.http.HttpStatus;

import java.io.IOException;

public class RequestUtil {

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.51 Safari/537.36";

    public static Document requestHtml(String url) {
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
                return html;
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
        return null;
    }

}

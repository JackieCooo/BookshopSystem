package com.bookshop.backend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bookshop.backend.data.News;
import com.bookshop.backend.jsonconvert.JsonResult;
import com.bookshop.backend.mapper.NewsMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NewsController {

    @Resource
    private NewsMapper newsMapper;

    @GetMapping("/news")
    public JsonResult<ArrayList<News>> sendNewsChart(@RequestParam(required = false, defaultValue = "-1") Integer num) {
        List<News> news = newsMapper.selectList(null);
        num = num == -1 || num > news.size() ? news.size() : num;
        ArrayList<News> res = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            res.add(news.get(i));
        }
        return new JsonResult<>(res);
    }

    @GetMapping("/news/{id}")
    public JsonResult<News> sendNews(@PathVariable Integer id) {
        QueryWrapper<News> newsWrapper = new QueryWrapper<>();
        newsWrapper.eq("id", id);
        News news = newsMapper.selectOne(newsWrapper);
        return new JsonResult<>(news);
    }
}

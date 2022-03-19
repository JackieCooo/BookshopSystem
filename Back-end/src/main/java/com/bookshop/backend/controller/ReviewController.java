package com.bookshop.backend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bookshop.backend.data.Review;
import com.bookshop.backend.mapper.ReviewChartMapper;
import com.bookshop.backend.mapper.ReviewMapper;
import com.bookshop.backend.data.ReviewChart;
import com.bookshop.backend.jsonconvert.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ReviewController {

    @Autowired
    private ReviewMapper reviewMapper;
    @Autowired
    private ReviewChartMapper reviewChartMapper;

    @GetMapping("/review/chart")
    public JsonResult<ArrayList<Review>> getReviewChart(@RequestParam(required = false, defaultValue = "-1") Integer num) {
        Long cnt = reviewChartMapper.selectCount(null);
        if (num == -1 || num > cnt) {
            num = cnt.intValue();
        }
        QueryWrapper<ReviewChart> reviewChartWrapper = new QueryWrapper<>();
        reviewChartWrapper.le("rank_num", num);
        List<ReviewChart> charts = reviewChartMapper.selectList(reviewChartWrapper);

        ArrayList<Review> res = new ArrayList<>();
        QueryWrapper<Review> reviewWrapper = new QueryWrapper<>();
        for (int i = 0; i < num; i++) {
            reviewWrapper.select("id", "title", "author", "date", "intro", "book_id").eq("id", charts.get(i).getReviewId());
            Review review = reviewMapper.selectOne(reviewWrapper);
            res.add(review);
            reviewWrapper.clear();
        }

        return new JsonResult<>(res);
    }

    @GetMapping("/review/{id}")
    public JsonResult<Review> getReview(@PathVariable Integer id) {
        QueryWrapper<Review> reviewWrapper = new QueryWrapper<>();
        reviewWrapper.select("id", "book_id", "title", "content", "author", "date").eq("id", id);
        Review review = reviewMapper.selectOne(reviewWrapper);
        return new JsonResult<>(review);
    }

}

package com.bookshop.backend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bookshop.backend.data.Chart;
import com.bookshop.backend.data.ChartType;
import com.bookshop.backend.mapper.BookMapper;
import com.bookshop.backend.mapper.ChartMapper;
import com.bookshop.backend.mapper.ChartTypeMapper;
import com.bookshop.backend.data.Book;
import com.bookshop.backend.jsonconvert.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ChartController {

    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private ChartMapper chartMapper;
    @Autowired
    private ChartTypeMapper chartTypeMapper;

    /**
     * 从数据库中提取榜单信息
     * @param type 榜单类型
     * @param detail 返回信息的详细程度
     *               1：除图书简介、作者简介、目录、ISBN号的全部内容
     *               2：只含书号、书名、作者名
     *               3：只含书号、书名
     *               4：只含书号
     *               5：只含书号、书名、图书简介
     * @param num 返回条数
     *            默认值为-1，返回能找到的全部
     */
    @GetMapping("/chart/{type}")
    public JsonResult<ArrayList<Book>> sendChartInfo(@PathVariable String type, @RequestParam(required = false, defaultValue = "1") Integer detail, @RequestParam(required = false, defaultValue = "-1") Integer num) {

        // 获取榜单类型
        QueryWrapper<ChartType> chartTypeWrapper = new QueryWrapper<>();
        chartTypeWrapper.eq("name", type);
        Integer typeId = chartTypeMapper.selectOne(chartTypeWrapper).getId();

        // 获取榜单信息
        QueryWrapper<Chart> chartWrapper = new QueryWrapper<>();
        chartWrapper.eq("type_id", typeId).orderByAsc("rank_num");
        List<Chart> charts = chartMapper.selectList(chartWrapper);

        // 根据指定的详细程度获取对应书本的信息
        if (num == -1 || num > charts.size()) num = charts.size();
        ArrayList<Book> res = new ArrayList<>();
        QueryWrapper<Book> bookWrapper = new QueryWrapper<>();
        if (detail == 1) {
            for (int i = 0; i < num; ++i) {
                bookWrapper.select("id", "name", "author", "date", "publisher", "price", "has_e_book", "has_secondhand_book").eq("id", charts.get(i).getBookId());
                Book book = bookMapper.selectOne(bookWrapper);
                res.add(book);
                bookWrapper.clear();
            }
        }
        else if (detail == 2) {
            for (int i = 0; i < num; ++i) {
                bookWrapper.select("id", "name", "author").eq("id", charts.get(i).getBookId());
                Book book = bookMapper.selectOne(bookWrapper);
                res.add(book);
                bookWrapper.clear();
            }
        }
        else if (detail == 3) {
            for (int i = 0; i < num; i++) {
                bookWrapper.select("id", "name").eq("id", charts.get(i).getBookId());
                Book book = bookMapper.selectOne(bookWrapper);
                res.add(book);
                bookWrapper.clear();
            }
        }
        else if (detail == 4) {
            for (int i = 0; i < num; i++) {
                bookWrapper.select("id").eq("id", charts.get(i).getBookId());
                Book book = bookMapper.selectOne(bookWrapper);
                res.add(book);
                bookWrapper.clear();
            }
        }
        else if (detail == 5) {
            for (int i = 0; i < num; i++) {
                bookWrapper.select("id", "name", "book_introduction").eq("id", charts.get(i).getBookId());
                Book book = bookMapper.selectOne(bookWrapper);
                res.add(book);
                bookWrapper.clear();
            }
        }
        else {
            return new JsonResult<>("1", "参数错误");
        }

        return new JsonResult<>(res);
    }
}

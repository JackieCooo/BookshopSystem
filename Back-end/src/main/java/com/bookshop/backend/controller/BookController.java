package com.bookshop.backend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bookshop.backend.data.Book;
import com.bookshop.backend.jsonconvert.JsonResult;
import com.bookshop.backend.mapper.BookMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class BookController {
    
    @Resource
    private BookMapper bookMapper;

    @GetMapping("/book/{id}")
    public JsonResult<Book> sendBookInfo(@PathVariable Integer id) {
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        Book book = bookMapper.selectOne(wrapper);
        if (book != null) {
            return new JsonResult<>(book);
        }
        return new JsonResult<>("1", "请求失败");
    }

    @GetMapping("/book/pic/{id}")
    public ResponseEntity<byte[]> sendBookPic(@PathVariable Integer id) throws IOException {
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        wrapper.select("id", "pic").eq("id", id);
        byte[] raw = bookMapper.selectOne(wrapper).getPic();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(raw, headers, HttpStatus.OK);
    }

}

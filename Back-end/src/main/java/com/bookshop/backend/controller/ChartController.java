package com.bookshop.backend.controller;

import com.bookshop.backend.data.BookInfo;
import com.bookshop.backend.jsonconvert.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChartController {

    @RequestMapping("/info")
    public JsonResult<BookInfo> getInfo() {
        BookInfo info = new BookInfo(1, "test");
        return new JsonResult<>(info);
    }
}

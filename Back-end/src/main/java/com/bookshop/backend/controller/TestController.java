package com.bookshop.backend.controller;

import com.bookshop.backend.jsonconvert.JsonResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/one")
    public JsonResult<String> getData1() {
        return new JsonResult<>("Hello from page 1");
    }

    @GetMapping("/two")
    public JsonResult<String> getData2() {
        return new JsonResult<>("Hello from page 2");
    }

/*
    @GetMapping("pic")
    public ResponseEntity<byte[]> getPic() {
        InputStream input = new
    }
*/
}

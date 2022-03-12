package com.bookshop.backend.controller;

import com.bookshop.backend.data.ShoppingCartInfo;
import com.bookshop.backend.jsonconvert.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/reg")
    public JsonResult<String> userSignup(@RequestParam String username, @RequestParam String password) {
        return new JsonResult<>();
    }

    @GetMapping("/login")
    public JsonResult<String> userLogin(@RequestParam String username, @RequestParam String password) {
        return new JsonResult<>();
    }

    @GetMapping("/cart")
    public JsonResult<ShoppingCartInfo> getShoppingCartInfo() {
        return new JsonResult<>();
    }

}

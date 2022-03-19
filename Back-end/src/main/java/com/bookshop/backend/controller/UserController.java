package com.bookshop.backend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bookshop.backend.callback.UserServiceCb;
import com.bookshop.backend.data.User;
import com.bookshop.backend.jsonconvert.JsonResult;
import com.bookshop.backend.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper mapper;

    @GetMapping("/reg")
    public JsonResult<String> userSignup(@RequestParam String username, @RequestParam String password) {
        return new JsonResult<>();
    }

    @GetMapping("/login")
    public JsonResult<User> userLogin(@RequestParam String username, @RequestParam String password) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", username);
        if (mapper.selectCount(wrapper) != 0) {
            wrapper.clear();
            wrapper.eq("name", username).eq("password", password);
            User user = mapper.selectOne(wrapper);
            if (user != null) {
                System.out.println(UserServiceCb.Ok);
                return new JsonResult<>(user);
            }
            else {
                System.out.println(UserServiceCb.PasswordFailed);
            }
        }
        else {
            System.out.println(UserServiceCb.UserNotExist);
        }

        return new JsonResult<>("1", "请求失败");
    }

}

package com.bookshop.backend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bookshop.backend.callback.UserServiceCallback;
import com.bookshop.backend.data.User;
import com.bookshop.backend.jsonconvert.JsonResult;
import com.bookshop.backend.mapper.UserMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserMapper userMapper;

    @GetMapping("/reg")
    public JsonResult<User> userSignup(@RequestParam String username, @RequestParam String password) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("name").eq("name", username);
        Long cnt = userMapper.selectCount(wrapper);
        if (cnt == 0) {
            User user = new User();
            user.setName(username);
            user.setPassword(password);
            userMapper.insert(user);
            return new JsonResult<>(user);
        }
        else {
            System.out.println(UserServiceCallback.userExisted);
            return new JsonResult<>(UserServiceCallback.userExisted, "用户已存在");
        }
    }

    @GetMapping("/login")
    public JsonResult<User> userLogin(@RequestParam String username, @RequestParam String password) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", username);
        if (userMapper.selectCount(wrapper) != 0) {
            wrapper.clear();
            wrapper.eq("name", username).eq("password", password);
            User user = userMapper.selectOne(wrapper);
            if (user != null) {
                System.out.println(UserServiceCallback.ok);
                return new JsonResult<>(user);
            }
            else {
                System.out.println(UserServiceCallback.passwordFailed);
                return new JsonResult<>(UserServiceCallback.passwordFailed, "密码错误");
            }
        }
        else {
            System.out.println(UserServiceCallback.userNotExist);
            return new JsonResult<>(UserServiceCallback.userNotExist, "用户不存在");
        }
    }

}

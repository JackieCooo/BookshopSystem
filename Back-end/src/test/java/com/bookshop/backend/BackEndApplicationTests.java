package com.bookshop.backend;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bookshop.backend.callback.UserServiceCb;
import com.bookshop.backend.data.User;
import com.bookshop.backend.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BackEndApplicationTests {

    @Autowired
    private UserMapper mapper;

    @Test
    void contextLoads() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "admi");
        if (mapper.selectCount(wrapper) != 0) {
            wrapper.clear();
            wrapper.eq("name", "admin").eq("password", "1234567");
            User user = mapper.selectOne(wrapper);
            if (user != null) {
                System.out.println(UserServiceCb.Ok);
                System.out.println(user);
            }
            else {
                System.out.println(UserServiceCb.PasswordFailed);
            }
        }
        else {
            System.out.println(UserServiceCb.UserNotExist);
        }
    }

}

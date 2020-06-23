package com.conan.spring.service;

import com.alibaba.fastjson.JSON;
import com.conan.spring.SpringBaseTest;
import com.conan.spring.pojo.mybatis.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserSerivceImplTest extends SpringBaseTest{

    @Autowired
    private UserService userService;

    @Test
    public void getUser() {
        User user = userService.getUser(1);
        System.out.println(user);
        System.out.println(JSON.toJSONString(user));
    }
}

package com.conan.spring.controller;

import com.conan.spring.pojo.mybatis.User;
import com.conan.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyBatisController {

    @Autowired
    // IoC容器中存在两个名称相同的Bean，会报错，所以特别指定名称
    @Qualifier(value = "MybatisUserServiceImpl")
    private UserService userService;

    @RequestMapping(value = "/mybatis_getuser")
    @ResponseBody
    public User getUser(int id) {
        return userService.getUser(id);
    }
}

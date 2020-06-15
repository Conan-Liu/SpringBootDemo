package com.conan.spring.controller;

import com.conan.spring.pojo.template.User;
import com.conan.spring.service.JdbcTemplateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JdbcController {

    @Autowired
    private JdbcTemplateUserService jdbcTemplateUserService;

    @RequestMapping(value = "/jdbc_getuser", method = RequestMethod.GET)
    @ResponseBody
    public User getUser(int id) {
        User user=jdbcTemplateUserService.getUser(id);
        return user;
    }
}

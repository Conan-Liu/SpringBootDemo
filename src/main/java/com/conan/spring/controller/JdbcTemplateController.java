package com.conan.spring.controller;

import com.conan.spring.pojo.template.User;
import com.conan.spring.service.JdbcTemplateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 使用{@link org.springframework.jdbc.core.JdbcTemplate}作为模型层来处理业务逻辑
 */
@Controller
@RequestMapping(value = "/jdbc")
public class JdbcTemplateController {

    @Autowired
    private JdbcTemplateUserService jdbcTemplateUserService;

    @RequestMapping(value = "/getuser", method = RequestMethod.GET)
    @ResponseBody
    public User getUser(int id) {
        User user=jdbcTemplateUserService.getUser(id);
        return user;
    }
}

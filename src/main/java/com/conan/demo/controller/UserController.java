package com.conan.demo.controller;

import com.conan.demo.model.User;
import com.conan.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2019/1/2.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/userInfo")
    public String userInfo() {
        return "UserInfo";
    }

    @RequestMapping("/userId")
    public Object login(@PathVariable("id") Integer id) {
        return userService.getUser(id);
    }

    @RequestMapping("/userAll")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }
}
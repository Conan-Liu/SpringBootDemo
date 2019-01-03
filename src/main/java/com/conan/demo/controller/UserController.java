package com.conan.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.conan.demo.model.User;
import com.conan.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2019/1/2.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/userInfo")
    public String userInfo() {
        return "UserInfo";
    }

//    public Object add(@RequestBody User user) {
//        if (userService.findByName(user.getName()) != null) {
//            JSONObject json = new JSONObject();
//            json.put("message", "用户被禁用");
//            return json;
//        }
//
//        return userService.add(user);
//    }

    @RequestMapping("/userId")
    public Object login(@PathVariable("id") Integer id) {
        return userService.getUser(id);
    }
}
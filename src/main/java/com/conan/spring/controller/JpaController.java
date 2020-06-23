package com.conan.spring.controller;

import com.conan.spring.pojo.jpa.User;
import com.conan.spring.service.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/jpa")
public class JpaController {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @RequestMapping(value = "/getuser", method = RequestMethod.GET)
    @ResponseBody
    public User getUser(Integer id) {
        User user = userJpaRepository.findById(id).get();
        return user;
    }

    @RequestMapping(value = "/getalluser")
    @ResponseBody
    public List<User> getAllUsers() {
        List<User> list = userJpaRepository.findUsers("name");
        return list;
    }
}

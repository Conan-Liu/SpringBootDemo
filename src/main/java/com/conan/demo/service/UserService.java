package com.conan.demo.service;

import com.conan.demo.mapper.UserMapper;
import com.conan.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2019/1/2.
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User getUser(Integer id) {
        return userMapper.findOne(id);
    }
}
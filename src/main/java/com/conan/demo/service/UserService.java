package com.conan.demo.service;

import com.conan.demo.mapper.UserMapper;
import com.conan.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2019/1/2.
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public int addUser(User user) {
        return userMapper.insert(user);
    }

    public User getUser(Integer userId) {
        return userMapper.selectByUserId(userId);
    }

    public List<User> getAllUser() {
        return userMapper.selectAllUser();
    }
}
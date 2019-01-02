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
    private UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User add(User user) {
        userMapper.add(user);
        return findById(user.getId());
    }

    public User findById(int id) {
        User user = new User();
        user.setId(id);
        return userMapper.findOne(user);
    }

    public User findByName(String name){
        User param=new User();
        param.setName(name);
        return userMapper.findOne(param);
    }
}
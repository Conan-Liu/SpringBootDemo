package com.conan.demo.mapper;

import com.conan.demo.model.User;

import java.util.List;

/**
 * Created by Administrator on 2019/1/2.
 */
public interface UserMapper {
    int add(User user);

    User findOne(Integer id);

    List<User> getAllUser();
}
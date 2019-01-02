package com.conan.demo.mapper;

import com.conan.demo.model.User;

/**
 * Created by Administrator on 2019/1/2.
 */
public interface UserMapper {
    int add(User user);

    User findOne(User user);
}
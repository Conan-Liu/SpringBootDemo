package com.conan.spring.service;

import com.conan.spring.pojo.redis.User;

import java.util.List;

public interface UserRedisService {

    User getUser(int id);

    User insertUser(User user);

    // 修改用户，指定MyBatis参数名称
    User updateUserName(int id, String userName);

    // 查询用户，指定MyBatis参数名称
    List<User> findUsers(String userName, String note);

    int deleteUser(int id);
}

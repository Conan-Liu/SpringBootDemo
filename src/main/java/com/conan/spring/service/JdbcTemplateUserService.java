package com.conan.spring.service;

import com.conan.spring.pojo.template.User;

import java.util.List;

public interface JdbcTemplateUserService {

    User getUser(Integer id);

    List<User> findUsers(String userName, String note);

    int insertUser(User user);

    int updateUser(User user);

    int deleteUser(Integer id);
}

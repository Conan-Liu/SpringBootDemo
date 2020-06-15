package com.conan.spring.service;

import com.conan.spring.pojo.mybatis.User;

/**
 * 这是服务提供类，用于编写业务逻辑，提供服务接口
 *
 * 该接口可以省略，直接编写业务逻辑服务类
 */
public interface UserService {

    User getUser(int id);
}

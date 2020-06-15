package com.conan.spring.aop;

import com.conan.spring.ioc.User;

/**
 * 用户服务接口
 */
public interface UserService {

    void printUser(User user);

    void manyAspects();
}

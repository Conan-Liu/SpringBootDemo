package com.conan.spring.aop;

import com.conan.spring.ioc.User;
import org.springframework.stereotype.Service;

/**
 * 用户服务接口的实现类
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * 该方法作为连接点
     */
    @Override
    public void printUser(User user) {
        if (user == null) {
            throw new RuntimeException("用户参数注入异常...");
        }

        System.out.println(user);
    }
}

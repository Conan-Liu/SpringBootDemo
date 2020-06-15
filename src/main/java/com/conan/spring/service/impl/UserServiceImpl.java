package com.conan.spring.service.impl;

import com.conan.spring.dao.UserMapper;
import com.conan.spring.pojo.mybatis.User;
import com.conan.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// 容器中可能存在同名的Bean导致启动失败，为了避免歧义，直接指定名称
@Service(value = "MybatisUserServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 这里就是具体的业务逻辑，如下简单演示
     * 比如sex字段，可以从数据库读出来后，在方法内进行枚举值的转换，而不需要自定义TypeHandler
     */
    @Override
    public User getUser(int id) {
        return userMapper.getUser(id);
    }
}

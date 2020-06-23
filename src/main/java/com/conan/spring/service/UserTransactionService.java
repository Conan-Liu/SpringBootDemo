package com.conan.spring.service;

import com.conan.spring.pojo.mybatis.UserTransaction;

import java.util.List;

public interface UserTransactionService {

    UserTransaction getUser(int id);

    int insertUser(UserTransaction userTransaction);

    /**
     * 测试事务的传播行为
     */
    // 批量更新用户
    int insertUsers(List<UserTransaction> userList);
}

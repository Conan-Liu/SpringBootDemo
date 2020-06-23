package com.conan.spring.dao;


import com.conan.spring.pojo.mybatis.UserTransaction;

public interface UserTransactionMapper {

    UserTransaction getUser(int id);

    int insertUser(UserTransaction userTransaction);
}

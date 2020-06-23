package com.conan.spring.service;

import com.conan.spring.pojo.mybatis.UserTransaction;

import java.util.List;

public interface UserBatchService {

    int insertUsers(List<UserTransaction> userList);
}

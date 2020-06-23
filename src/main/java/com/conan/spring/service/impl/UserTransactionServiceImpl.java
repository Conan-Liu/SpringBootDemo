package com.conan.spring.service.impl;

import com.conan.spring.dao.UserTransactionMapper;
import com.conan.spring.pojo.mybatis.UserTransaction;
import com.conan.spring.service.UserTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserTransactionServiceImpl implements UserTransactionService {

    @Autowired
    private UserTransactionMapper userTransactionMapper;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, timeout = 1)
    public UserTransaction getUser(int id) {
        return userTransactionMapper.getUser(id);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, timeout = 1, propagation = Propagation.NESTED)
    public int insertUser(UserTransaction userTransaction) {
        return userTransactionMapper.insertUser(userTransaction);
    }

    /**
     * 同一个类里面自身方法的调用，被称为自调用，从打印日志来看不能启用传播行为，也就是@Transactional失效了
     *
     * 原因： 自调用过程中，是类自身调用，不是代理对象去调用，不会产生AOP，也就不能把代码织入到约定的流程中
     * 所以，最好是一个Service调用另外一个Service
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public int insertUsers(List<UserTransaction> userList) {
        int count = 0;
        for (UserTransaction user : userList) {
            int i = insertUser(user);
            count += i;
        }
        return count;
    }
}

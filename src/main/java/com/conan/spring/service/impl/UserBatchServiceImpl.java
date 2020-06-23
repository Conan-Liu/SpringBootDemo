package com.conan.spring.service.impl;

import com.conan.spring.pojo.mybatis.UserTransaction;
import com.conan.spring.service.UserBatchService;
import com.conan.spring.service.UserTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserBatchServiceImpl implements UserBatchService {

    // 批量操作，调用单个事务的服务接口
    @Autowired
    private UserTransactionService userTransactionService;

    /**
     * 传播行为需要在不同类的不同方法之间调用才行
     *
     * 1. 当propagation = Propagation.REQUIRED，且insertUser方法没有定义传播行为时，成功打印Participating in existing transaction
     *    使用现成的事务
     *
     * 2. 当propagation = Propagation.REQUIRED，且insertUser方法propagation = Propagation.REQUIRES_NEW时，成功打印Suspending current transaction, creating new transaction with name
     *    启动了新的数据库事务去执行insertUser，独立提交
     *
     * 3. 当propagation = Propagation.REQUIRED，且insertUser方法propagation = Propagation.NESTED时，成功打印Creating nested transaction with name
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public int insertUsers(List<UserTransaction> userList) {
        int count = 0;
        for (UserTransaction user : userList) {
            int i = userTransactionService.insertUser(user);
            count += i;
        }
        return count;
    }
}

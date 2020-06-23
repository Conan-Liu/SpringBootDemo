package com.conan.spring.pojo.mybatis;

import lombok.Data;

/**
 * 该User POJO类为了演示数据库事务
 */
@Data
public class UserTransaction {

    private int id;
    private String userName;
    private String note;
}

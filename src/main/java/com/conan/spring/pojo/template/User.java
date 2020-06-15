package com.conan.spring.pojo.template;

import com.conan.spring.pojo.SexEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * 准备与数据库表t_user对应的Pojo类
 */
@Getter
@Setter
public class User {

    // 注意这里要使用包装类，基本数据类型无法注入
    private Integer id;
    private String userName;
    private SexEnum sex;
    private String note;
}

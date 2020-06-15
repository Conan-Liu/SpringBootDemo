package com.conan.spring.pojo.mybatis;


import com.conan.spring.pojo.SexEnum;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
// Mybatis 的别名注解，指定别名为user，mapper文件中可以作为resultType来指定返回类型
@Alias(value = "user")
public class User {

    private Integer id;

    private String userName;

    // 这里是一个枚举类型，需要自定义一个TypeHandler来处理该数据类型
    private SexEnum sex;

    private String note;

}

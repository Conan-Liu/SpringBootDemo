package com.conan.spring.pojo.redis;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * User对象数据导入Redis，需要能够被序列化
 */
@Data
@Alias("redis_user")
public class User implements Serializable{


    private static final long serialVersionUID = -8256031395037644936L;

    private int id;
    private String userName;
    private String note;
}

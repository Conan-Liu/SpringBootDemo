package com.conan.spring.dao;

import com.conan.spring.pojo.mybatis.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * dao层接口对应 mapper.xml
 * dao层接口无法新建对象，也就无法放入IoC容器中，有三种方式解决
 * 1. MapperFactoryBean
 * 2. MapperScannerConfigurer
 * 3. 可以在启动类上使用@MapperScan注解来扫描装配进容器
 * UserMapper 对应 UserMapper.xml
 */

/**
 * 注意 @Repository是Spring对持久层的注解，@Mapper是Mybatis对持久层的注解
 * 对于@Repository注解，需要在启动类上配合@MapperScan使用
 * 而@Mapper可以单独使用，不需要配置@MapperScan
 *
 * 最好的就是简单点，启动类上使用@MapperScan，这两个都可以省略
 */
// @Repository
// @Mapper
public interface UserMapper {

    User getUser(int id);
}

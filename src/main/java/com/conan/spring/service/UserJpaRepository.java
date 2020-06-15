package com.conan.spring.service;

import com.conan.spring.pojo.jpa.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 不需要实现类和接口方法，Spring根据JPA接口规范帮我们完成
 *
 * 可以自己定义一些接口方法
 */
public interface UserJpaRepository extends JpaRepository<User,Integer> {

    // Jpa使用Hibernate，自动生成sql，可以通过@Query注解灵活使用
    @Query("from user where user_name like concat('%',?1,'%')")
    List<User> findUsers(String userName);
}

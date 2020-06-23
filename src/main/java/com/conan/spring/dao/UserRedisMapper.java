package com.conan.spring.dao;


import com.conan.spring.pojo.redis.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// 标识为DAO层，可有可无
@Repository
public interface UserRedisMapper {

    User getUser(int id);

    int insertUser(User user);

    int updateUser(User user);

    List<User> findUsers(@Param("userName")String userName,@Param("note")String note);

    int deleteUser(int id);
}

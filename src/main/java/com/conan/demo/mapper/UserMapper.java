package com.conan.demo.mapper;

import com.conan.demo.model.User;

import java.util.List;

/**
 * Created by Administrator on 2019/1/2.
 */
public interface UserMapper {
    int deleteByUserId(Integer userId);

    int insert(User record);

//    int insertSelective(User record);

    User selectByUserId(Integer userId);

//    int updateByUserIdSelective(User record);

    int updateByUserId(User record);

    List<User> selectAllUser();
}
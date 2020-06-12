package com.conan.demo.mapper;

import com.conan.demo.model.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    /**
     * 如下操作是基于xml配置
     */
    int deleteByUserId(Integer userId);

    int insert(User record);

//    int insertSelective(User record);

    User selectByUserId(Integer userId);

//    int updateByUserIdSelective(User record);

    int updateByUserId(User record);

    List<User> selectAllUser();


    /**
     * 基于注解
     */
    @Select("select * from t_user")
    List<User> allUser();
}
package com.conan.demo.service;

import com.conan.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2019/1/15.
 */
@Service
public class UserServiceTemplate {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> getAllUsers() {
        return jdbcTemplate.query("select * from t_user", new BeanPropertyRowMapper<User>(User.class));
    }
}
package com.conan.spring.service.impl;

import com.conan.spring.pojo.SexEnum;
import com.conan.spring.pojo.template.User;
import com.conan.spring.service.JdbcTemplateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class JdbcTemplateUserServiceImpl implements JdbcTemplateUserService {

    /**
     * 配置数据源后，SpringBoot的自动配置机制已经初始化了JdbcTemplate
     * 该类和装配入容器由Spring框架提供，可以直接使用
     * JdbcTemplate 可以支持多个sql执行，但是底层，数据库连接池会为每一条sql分配一个连接，两个sql则会用两个连接，每一个连接都会连接数据库和关闭，所以这种方式不推荐
     * 可以通过StatementCallback和ConnectionCallBack接口回调实现一个连接执行多条sql
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 获取映射关系，jdbcTemplate的映射关系需要自己实现RowMapper接口
    private RowMapper<User> getUserMapper() {
        // lambda 表达式
        RowMapper<User> userRowMapper1 = (ResultSet rs, int rowNum) -> {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUserName(rs.getString("user_name"));
            int sexId = rs.getInt("sex");
            SexEnum sex = SexEnum.getEnumById(sexId);
            user.setSex(sex);
            user.setNote(rs.getString("note"));
            return user;
        };

        // 常规写法
        RowMapper<User> userRowMapper = new RowMapper<User>() {

            @Override
            public User mapRow(ResultSet rs, int i) throws SQLException {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("user_name"));
                int sexId = rs.getInt("sex");
                SexEnum sex = SexEnum.getEnumById(sexId);
                user.setSex(sex);
                user.setNote(rs.getString("note"));
                return user;
            }
        };

        return userRowMapper;
    }

    @Override
    public User getUser(Integer id) {
        String sql = "select id,user_name,sex,note from t_user where id = ?";
        Object[] params = new Object[]{id};
        // 这里如果查不出结果会报错
        User user = jdbcTemplate.queryForObject(sql, params, getUserMapper());
        return user;
    }

    @Override
    public List<User> findUsers(String userName, String note) {
        String sql = "select id,user_name,sex,note from t_user where user_name like concat('%',?,'%') and note like concat('%',?,'%')";
        Object[] params = new Object[]{userName, note};
        List<User> userList = jdbcTemplate.query(sql, params, getUserMapper());
        return userList;
    }

    @Override
    public int insertUser(User user) {
        String sql = "insert into t_user(user_name,sex,note) values(?,?,?)";
        return jdbcTemplate.update(sql, user.getUserName(), user.getSex().getId(), user.getNote());
    }

    @Override
    public int updateUser(User user) {
        String sql = "update t_user set user_name=?,sex=?,note=? where id =?";
        return jdbcTemplate.update(sql, user.getUserName(), user.getSex().getId(), user.getNote(), user.getId());
    }

    @Override
    public int deleteUser(Integer id) {
        String sql = "delete from t_user where id = ?";
        return jdbcTemplate.update(sql, id);
    }


}

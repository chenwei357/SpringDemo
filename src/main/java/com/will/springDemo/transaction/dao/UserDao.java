package com.will.springDemo.transaction.dao;

import com.will.springDemo.transaction.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @Author chenwei
 * @Date 2019-07-04
 */
@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insert(User user) {
        return jdbcTemplate.update("insert into user(user_name, user_pwd) values(?, ?)", user.getUser_name(), user.getUser_pwd());
    }

    public int update(User user) {
        return jdbcTemplate.update("update user set user_name = ?, user_pwd = ? where id = ?", user.getUser_name(), user.getUser_pwd(), user.getId());
    }

    public User findByName(String name) {
        return jdbcTemplate.queryForObject("SELECT * FROM USER WHERE USER_NAME = ?", new BeanPropertyRowMapper<User>(User.class), name);
    }

}

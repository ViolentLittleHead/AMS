package com.landian.dao.impl;

import com.landian.dao.UserDao;
import com.landian.domain.User;
import com.landian.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao {
    //声明JDBCTemplate对象公用
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    public User login(User loginUser){
        try {
            String sql = "select * from user where username = ? and password = ?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), loginUser.getUsername(), loginUser.getPassword());

            return user;
        }catch (DataAccessException e){
            e.printStackTrace();    //记录日志
            return null;
        }
    }
    public boolean register(User register){
        try {
            String sql = "insert into user(username,password,email) values(?,?,?)";
            template.update(sql,register.getUsername(),register.getPassword(),register.getEmail());
            return true;
        }catch (DataAccessException e){
            e.printStackTrace();
            return false;
        }
    }
}

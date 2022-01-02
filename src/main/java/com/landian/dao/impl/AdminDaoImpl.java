package com.landian.dao.impl;


import com.landian.dao.AdminDao;
import com.landian.domain.Admin;
import com.landian.domain.User;
import com.landian.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class AdminDaoImpl implements AdminDao {
    //声明JDBCTemplate对象公用
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public Admin login(Admin admin) {
        String sql = "select * from admin where adminname=? and password=?";
        Admin admin1 = template.queryForObject(sql, new BeanPropertyRowMapper<Admin>(Admin.class), admin.getAdminname(), admin.getPassword());
        return admin1;
    }

    @Override
    public int findTotalCount() {
        String sql = "select count(*) from user ";

        return template.queryForObject(sql,Integer.class);
    }

    @Override
    public List<User> findByPage(int start, int rows) {
        String sql = "select * from user limit ? , ?";

        return template.query(sql,new BeanPropertyRowMapper<User>(User.class),start,rows);

    }

    @Override
    public void frozen(String username) {
        String sql = "UPDATE USER SET STATUS = '0' WHERE username = ?";
        template.update(sql,username);
    }

    @Override
    public void thaw(String username) {
        String sql = "UPDATE USER SET STATUS = '1' WHERE username = ?";
        template.update(sql,username);
    }


}

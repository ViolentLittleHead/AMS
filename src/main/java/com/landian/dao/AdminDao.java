package com.landian.dao;

import com.landian.domain.Admin;
import com.landian.domain.User;

import java.util.List;

public interface AdminDao {

    /**
     * 管理员登录
     * @param admin
     * @return
     */
    Admin login(Admin admin);

    /**
     * 查找总的用户记录数
     * @return
     */
    int findTotalCount();

    /**
     * 查找指定行数用户记录数
     * @param start
     * @param rows
     * @return
     */
    List<User> findByPage(int start,int rows);

    /**
     * 冻结账号
     * @param username
     */
    void frozen(String username);

    /**
     * 解冻账号
     * @param username
     */
    void thaw(String username);
}

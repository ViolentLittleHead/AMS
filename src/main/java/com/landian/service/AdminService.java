package com.landian.service;

import com.landian.domain.Admin;
import com.landian.domain.PageBean;
import com.landian.domain.User;

public interface AdminService {

    /**
     * 管理员登录
     * @param admin
     * @return
     */
    Admin login(Admin admin);

    /**
     * 找到所有的用户信息
     * @param currentPage
     * @param rows
     * @return
     */
    PageBean<User> findAllUserByPage(String currentPage,String rows);

    /**
     * 冻结账号集
     * @param usernames
     */
    void frozen(String[] usernames);

    /**
     * 解冻账号集
     * @param usernames
     */
    void thaw(String[] usernames);
}

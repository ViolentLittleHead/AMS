package com.landian.dao;

import com.landian.domain.User;

public interface UserDao {
    /**
     * 登录方法
     * @param loginUser
     * @return User
     */
    public User login(User loginUser);

    /**
     * 注册方法
     * @param register
     * @return boolean
     */
    public boolean register(User register);
}

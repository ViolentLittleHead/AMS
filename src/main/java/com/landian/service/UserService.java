package com.landian.service;

import com.landian.domain.User;

public interface UserService {
    /**
     * 登录service
     * @param loginUser
     * @return User
     */
    public User login(User loginUser);

    /**
     * 注册service
     * @param registerUser
     * @return boolean
     */
    public boolean register(User registerUser);
}

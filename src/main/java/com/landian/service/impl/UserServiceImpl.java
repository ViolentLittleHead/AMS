package com.landian.service.impl;

import com.landian.dao.UserDao;
import com.landian.dao.impl.UserDaoImpl;
import com.landian.domain.User;
import com.landian.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public User login(User loginUser) {
        return userDao.login(loginUser);
    }

    @Override
    public boolean register(User registerUser) {
        return userDao.register(registerUser);
    }
}

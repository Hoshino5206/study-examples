package com.hoshino.spring.ioc.service.impl;

import com.hoshino.spring.ioc.dao.UserDao;
import com.hoshino.spring.ioc.service.UserService;

/**
 * @author Yy_hoshino
 * @date 2021-04-08 23:23
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    // 构造器注入
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    // setter方法注入
    /*public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }*/

    @Override
    public void save() {
        userDao.save();
        System.out.println("save runned...");
    }

}

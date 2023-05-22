package com.hoshino.spring.aop.jdk_proxy.dao.impl;

import com.hoshino.spring.aop.jdk_proxy.dao.UserDao;

/**
 * @author Yy_hoshino
 * @date 2021-04-10 1:39
 */
public class UserDaoImpl implements UserDao {

    @Override
    public void addUser() {
        System.out.println("添加用户...");
    }

    @Override
    public void deleteUser() {
        System.out.println("删除用户...");
    }

}

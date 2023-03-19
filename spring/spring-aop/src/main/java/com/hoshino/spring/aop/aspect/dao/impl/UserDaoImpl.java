package com.hoshino.spring.aop.aspect.dao.impl;

import com.hoshino.spring.aop.aspect.dao.UserDao;

/**
 * @author Yy_hoshino
 * @date 2021-04-10 1:39
 */
// 目标类
public class UserDaoImpl implements UserDao {

    public void addUser() {
        System.out.println("添加用户...");
    }

    public void deleteUser() {
        System.out.println("删除用户...");
    }

}

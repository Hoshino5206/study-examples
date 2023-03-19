package com.hoshino.spring.aop.cglib_proxy.dao;

/**
 * @author Yy_hoshino
 * @date 2021-04-10 1:52
 */
public class UserDao {

    public void addUser() {
        System.out.println("添加用户");
    }

    public void deleteUser() {
        System.out.println("删除用户");
    }

}

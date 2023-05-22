package com.hoshino.spring.aop.aspectJ_xml.dao.impl;

import com.hoshino.spring.aop.aspectJ_xml.dao.UserDao;

/**
 * @author Yy_hoshino
 * @date 2021-04-10 14:02
 */
public class UserDaoImpl implements UserDao {

    @Override
    public void save() {
        System.out.println("save running...");
    }

    @Override
    public void delete() {
        System.out.println("delete running...");
    }

    @Override
    public void update() {
        System.out.println("update running...");
    }

    @Override
    public void find() {
        int i = 1/0;
        System.out.println("find running...");
    }

}

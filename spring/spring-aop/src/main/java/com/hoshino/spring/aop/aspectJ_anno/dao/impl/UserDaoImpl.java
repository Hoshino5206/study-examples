package com.hoshino.spring.aop.aspectJ_anno.dao.impl;

import com.hoshino.spring.aop.aspectJ_anno.dao.UserDao;
import org.springframework.stereotype.Component;

/**
 * @author Yy_hoshino
 * @date 2021-04-10 14:02
 */
@Component("userDao")
public class UserDaoImpl implements UserDao {

    public void save() {
        System.out.println("save running...");
    }

    public void delete() {
        System.out.println("delete running...");
    }

    public void update() {
        System.out.println("update running...");
    }

    public void find() {
        int i = 1/0;
        System.out.println("find running...");
    }

}

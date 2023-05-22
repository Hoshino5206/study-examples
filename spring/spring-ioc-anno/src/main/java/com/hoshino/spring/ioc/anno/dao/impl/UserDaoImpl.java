package com.hoshino.spring.ioc.anno.dao.impl;

import com.hoshino.spring.ioc.anno.dao.UserDao;
import org.springframework.stereotype.Repository;

/**
 * @author Yy_hoshino
 * @date 2021-04-09 23:56
 */
//@Component("userDao")
@Repository("userDao")
public class UserDaoImpl implements UserDao {

    @Override
    public void save(){
        System.out.println("save running...");
    }

}

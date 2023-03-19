package com.hoshino.spring.ioc.util;

import com.hoshino.spring.ioc.dao.UserDao;
import com.hoshino.spring.ioc.dao.impl.UserDaoImpl;

/**
 * @author Yy_hoshino
 * @date 2021-04-08 23:26
 */
public class MyBeanFactory {

    // 工厂静态方法
    public static UserDao createStaticUserDao() {
        return new UserDaoImpl();
    }

    // 工厂实例方法
    public UserDao createUserDao(){
        return new UserDaoImpl();
    }

}

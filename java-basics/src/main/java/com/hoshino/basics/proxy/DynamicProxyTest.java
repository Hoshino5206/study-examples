package com.hoshino.basics.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author huangyuehao
 * @date 2022-12-13
 */
public class DynamicProxyTest {

    public static void main (String[] args) {
        UserDao userDao = new UserDaoImpl();
        InvocationHandler handler = new DynamicProxy(userDao);

        ClassLoader loader = userDao.getClass().getClassLoader();
        Class<?>[] interfaces = userDao.getClass().getInterfaces();

        UserDao proxy = (UserDao) Proxy.newProxyInstance(loader, interfaces, handler);
        proxy.saveUser();
    }

}

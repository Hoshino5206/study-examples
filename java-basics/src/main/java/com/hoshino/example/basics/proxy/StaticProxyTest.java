package com.hoshino.example.basics.proxy;

/**
 * @author huangyuehao
 * @date 2022-12-13
 */
public class StaticProxyTest {

    public static void main(String[] args) {
        UserDao userDao = new UserDaoImpl();
        StaticProxy staticProxy = new StaticProxy(userDao);
        staticProxy.saveUser();
    }

}

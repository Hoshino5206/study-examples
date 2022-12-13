package com.hoshino.example.basics.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author huangyuehao
 * @date 2022-12-13
 */
public class DynamicProxy implements InvocationHandler {

    private UserDao userDao;

    public DynamicProxy(UserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        saveUserStart();
        Object obj = method.invoke(userDao, args);
        saveUserDone();
        return obj;
    }

    public void saveUserStart() {
        System.out.println("---- 开始插入 ----");
    }

    public void saveUserDone() {
        System.out.println("---- 插入完成 ----");
    }
}

package com.hoshino.basics.proxy;

/**
 * @author huangyuehao
 * @date 2022-12-13
 */
public class StaticProxy {

    private UserDao userDao;

    public StaticProxy (UserDao userDao){
        this.userDao = userDao;
    }

    public void saveUser() {
        System.out.println(" ---- 代理开始 ---- ");
        userDao.saveUser();
        System.out.println(" ---- 代理结束 ----");
    }

}

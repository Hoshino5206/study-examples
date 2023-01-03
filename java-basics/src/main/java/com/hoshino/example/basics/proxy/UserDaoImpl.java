package com.hoshino.example.basics.proxy;

/**
 * @author huangyuehao
 * @date 2022-12-13
 */
public class UserDaoImpl implements UserDao{

    @Override
    public void saveUser() {
        System.out.println(" ---- 保存用户 ---- ");
    }

}

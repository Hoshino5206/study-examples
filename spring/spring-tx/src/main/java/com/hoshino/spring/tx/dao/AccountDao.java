package com.hoshino.spring.tx.dao;

/**
 * @author Yy_hoshino
 * @date 2021-04-14 0:35
 */
public interface AccountDao {

    public void out(String outMan,double money);

    public void in(String inMan,double money);

}

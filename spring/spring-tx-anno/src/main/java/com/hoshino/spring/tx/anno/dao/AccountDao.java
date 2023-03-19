package com.hoshino.spring.tx.anno.dao;

/**
 * @author Yy_hoshino
 * @date 2021-04-14 23:26
 */
public interface AccountDao {

    public void out(String outMan,double money);

    public void in(String inMan,double money);

}

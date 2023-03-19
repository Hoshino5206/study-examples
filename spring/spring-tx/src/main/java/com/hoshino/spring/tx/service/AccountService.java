package com.hoshino.spring.tx.service;

/**
 * @author Yy_hoshino
 * @date 2021-04-14 0:49
 */
public interface AccountService {

    public void transfer(String outMan, String inMan, double money);

}

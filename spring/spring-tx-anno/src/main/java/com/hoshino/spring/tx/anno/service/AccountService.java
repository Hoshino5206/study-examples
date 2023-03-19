package com.hoshino.spring.tx.anno.service;

/**
 * @author Yy_hoshino
 * @date 2021-04-14 23:32
 */
public interface AccountService {

    public void transfer(String outMan, String inMan, double money);

}

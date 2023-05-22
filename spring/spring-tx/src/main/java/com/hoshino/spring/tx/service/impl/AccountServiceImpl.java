package com.hoshino.spring.tx.service.impl;

import com.hoshino.spring.tx.dao.AccountDao;
import com.hoshino.spring.tx.service.AccountService;

/**
 * @author Yy_hoshino
 * @date 2021-04-14 0:49
 */
public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public void transfer(String outMan, String inMan, double money) {
        accountDao.out(outMan, money);
        int i = 1 / 0;
        accountDao.in(inMan, money);
    }

}

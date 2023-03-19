package com.hoshino.spring.tx.anno.service.impl;

import com.hoshino.spring.tx.anno.dao.AccountDao;
import com.hoshino.spring.tx.anno.service.AccountService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Yy_hoshino
 * @date 2021-04-14 23:32
 */

/**
 * isolation:隔离级别，默认值DEFAULT
 * propagation:传播行为，默认值REQUIRED
 * timeout:超时时间，默认值-1
 * read-only:只读，默认值false
 */
@Service("accountService")
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, timeout = -1, readOnly = false)
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    public void transfer(String outMan, String inMan, double money) {
        accountDao.out(outMan, money);
        int i = 1 / 0;
        accountDao.in(inMan, money);
    }

}

package com.hoshino.spring.tx.anno.dao.impl;

import com.hoshino.spring.tx.anno.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author Yy_hoshino
 * @date 2021-04-14 23:26
 */
@Repository("accountDao")
public class AccountDaoImpl implements AccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void out(String outMan, double money) {
        String sql = "update account set money = money - ? where name = ?";
        jdbcTemplate.update(sql, money, outMan);
    }

    @Override
    public void in(String inMan, double money) {
        String sql = "update account set money = money + ? where name = ?";
        jdbcTemplate.update(sql, money, inMan);
    }

}

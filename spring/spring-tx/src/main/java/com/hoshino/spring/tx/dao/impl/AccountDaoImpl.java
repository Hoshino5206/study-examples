package com.hoshino.spring.tx.dao.impl;

import com.hoshino.spring.tx.dao.AccountDao;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author Yy_hoshino
 * @date 2021-04-14 0:36
 */
public class AccountDaoImpl implements AccountDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

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

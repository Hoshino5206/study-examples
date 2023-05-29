package com.hoshino.springboot.jdbc.service;

import com.hoshino.springboot.jdbc.entity.Account;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Akino
 * @date 2023-05-30
 */
@Service
public class JdbcService {

    @Resource
    private JdbcTemplate jdbcTemplate;
    /**
     * 创建account表
     */
    public void executeTest() {
        jdbcTemplate.execute("create table account( " +
                "id int primary key auto_increment," +
                "name varchar(50) ," +
                "money double)");
        System.out.println("账户表 account 创建成功!");
    }

    /**
     * 添加账户
     */
    public void addTest() {
        String sql = "insert into account(name, money) value(?,?)";
        Account account = new Account();
        account.setName("Tom");
        account.setMoney(1000.00);
        // 定义数组来存放SQL语句中的参数
        Object[] obj = new Object[] {
                account.getName(),
                account.getMoney()
        };
        jdbcTemplate.update(sql, obj);
    }

    /**
     * batchUpdate批量添加账户
     */
    public void addsTest() {
        String sql = "insert into account(name, money) value(?,?)";
        List<Object[]> obj = new ArrayList<Object[]>();
        obj.add(new Object[]{"Tom","1000"});
        obj.add(new Object[]{"Jack","3500"});
        obj.add(new Object[]{"Kit","2000"});
        obj.add(new Object[]{"Emi","5000"});
        obj.add(new Object[]{"Peter","2000"});
        jdbcTemplate.batchUpdate(sql,obj);
    }

    /**
     * 通过id更新账户的名字和余额
     */
    public void updateTest() {
        String sql = "update account set name=?, money=? where id = ?";
        Account account = new Account();
        account.setId(2);
        account.setName("Jack");
        account.setMoney(3500.00);
        // 定义数组来存放SQL语句中的参数
        Object[] params = new Object[] {
                account.getName(),
                account.getMoney(),
                account.getId()
        };
        jdbcTemplate.update(sql,params);
    }

    /**
     * 通过id删除账户
     */
    public void deleteTest() {
        String sql = "delete from account where id = ? ";
        jdbcTemplate.update(sql,3);
    }

    /**
     * 查询所有账户信息
     */
    public void findAccountAllTest() {
        String sql = "select * from account";
        // 创建一个新的BeanPropertyRowMapper对象
        RowMapper<Account> rowMapper = new BeanPropertyRowMapper<Account>(Account.class);
        // 执行静态的SQL查询，并通过RowMapper返回结果
        List<Account> query = jdbcTemplate.query(sql, rowMapper);
        for (Account account : query) {
            System.out.println(account);
        }
    }

    /**
     * 通过id查询账户数据信息
     */
    public void findAccountByIdTest() {
        String sql = "select * from account where id = ?";
        // 创建一个新的BeanPropertyRowMapper对象
        RowMapper<Account> rowMapper = new BeanPropertyRowMapper<Account>(Account.class);
        // 将id绑定到SQL语句中，并通过RowMapper返回一个Object类型的单行记录
        Account account = jdbcTemplate.queryForObject(sql, rowMapper, 2);
        System.out.println(account);
    }

    /**
     * 通过id查询账聚合函数的统计
     */
    public void countIdByAccountTest() {
        String sql = "select count(id) from account";
        Long total = jdbcTemplate.queryForObject(sql, long.class);
        System.out.println(total);
    }

}

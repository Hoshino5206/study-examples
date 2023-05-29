package com.hoshino.springboot.jdbc;

import com.hoshino.springboot.jdbc.service.JdbcService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author Akino
 * @date 2023-05-30
 */
@SpringBootTest(classes = JdbcApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class JdbcServiceTest {

    @Resource
    private JdbcService jdbcService;

    @Test
    public void createTable() {
        jdbcService.createTable();
    }

    @Test
    public void add() {
        jdbcService.add();
    }

    @Test
    public void batchAdd() {
        jdbcService.batchAdd();
    }

    @Test
    public void updateById() {
        jdbcService.updateById();
    }

    @Test
    public void deleteById() {
        jdbcService.deleteById();
    }

    @Test
    public void selectAll() {
        jdbcService.selectAll();
    }

    /**
     * 通过id查询账户数据信息
     */
    @Test
    public void selectById() {
        jdbcService.selectById();
    }

    /**
     * 通过id查询账聚合函数的统计
     */
    @Test
    public void selectByIdCount() {
        jdbcService.selectByIdCount();
    }

}

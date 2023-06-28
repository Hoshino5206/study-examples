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
    public void createTableTest() {
        jdbcService.createTable();
    }

    @Test
    public void addTest() {
        jdbcService.add();
    }

    @Test
    public void batchAddTest() {
        jdbcService.batchAdd();
    }

    @Test
    public void updateByIdTest() {
        jdbcService.updateById();
    }

    @Test
    public void deleteByIdTest() {
        jdbcService.deleteById();
    }

    @Test
    public void selectAllTest() {
        jdbcService.selectAll();
    }

    /**
     * 通过id查询账户数据信息
     */
    @Test
    public void selectByIdTest() {
        jdbcService.selectById();
    }

    /**
     * 通过id查询账聚合函数的统计
     */
    @Test
    public void selectByIdCountTest() {
        jdbcService.selectByIdCount();
    }

}

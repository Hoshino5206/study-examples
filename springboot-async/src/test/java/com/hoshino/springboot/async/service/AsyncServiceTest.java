package com.hoshino.springboot.async.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author huangyuehao
 * @date 2023-05-25
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AsyncServiceTest {

    @Resource
    private AsyncService asyncService;

    @Test
    public void testAsync() throws Exception {
        asyncService.dealAsyncTask();
    }
}

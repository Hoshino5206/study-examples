package com.hoshino.springboot.async.service;

import com.hoshino.springboot.async.AsyncApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

/**
 * @author huangyuehao
 * @date 2023-05-25
 */
@SpringBootTest(classes = AsyncApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class AsyncServiceTest {

    @Resource
    private AsyncService asyncService;

    @Test
    public void asyncTest() throws ExecutionException, InterruptedException {
        asyncService.dealAsyncTask();
    }

}

package com.hoshino.springboot.async.service;

import com.hoshino.springboot.async.task.AsyncTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author huangyuehao
 * @date 2023-05-16
 */
@Service
@Slf4j
public class AsyncService {

    @Resource
    private AsyncTask asyncTask;

    public void dealAsyncTask() throws InterruptedException, ExecutionException {

        asyncTask.dealNoReturnTask();

        Future<String> result = asyncTask.dealHaveReturnTask(5);

        log.info("主线程执行finished");
        log.info(result.get());
    }

}

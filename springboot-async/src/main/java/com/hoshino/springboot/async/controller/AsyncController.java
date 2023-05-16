package com.hoshino.springboot.async.controller;

import com.hoshino.springboot.async.task.AsyncTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author huangyuehao
 * @date 2023-05-16
 */
@RestController
@RequestMapping("/admin")
@Slf4j
public class AsyncController {

    @Resource
    private AsyncTask asyncTask;

    @PostMapping("/asyncTask")
    public void asyncTask() throws InterruptedException, ExecutionException {

        asyncTask.dealNoReturnTask();

        Future<String> result = asyncTask.dealHaveReturnTask(5);

        log.info("主线程执行finished");
        log.info(result.get());
    }

}

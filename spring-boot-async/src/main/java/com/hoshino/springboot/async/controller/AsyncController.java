package com.hoshino.springboot.async.controller;

import com.hoshino.springboot.async.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

/**
 * @author huangyuehao
 * @date 2023-05-16
 */
@RestController
@RequestMapping("/async/admin")
@Slf4j
public class AsyncController {

    @Resource
    private AsyncService asyncService;

    @RequestMapping("/task")
    public void asyncTask() throws ExecutionException, InterruptedException {
        asyncService.dealAsyncTask();
    }

}

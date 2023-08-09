package com.hoshino.basics.juc;

import com.hoshino.basics.thread.MyThreadPool;

import java.util.concurrent.*;

/**
 * @author huangyuehao
 * @date 2023-02-15
 */
public class MyFuture {

    public static void main(String[] args) {
        ExecutorService executorService = MyThreadPool.getExecutorService();

        Future<?> submit = executorService.submit(() -> {
            System.out.println(Thread.currentThread().getName());
        });
        try {
            String result = (String) submit.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }

}

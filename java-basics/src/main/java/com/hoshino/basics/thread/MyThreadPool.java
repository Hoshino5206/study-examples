package com.hoshino.basics.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Akino
 * @date 2023-05-09
 */
public class MyThreadPool {
    public static void main(String[] args) {
        /**
         * Executors工具类创建线程池
         */
        ExecutorService service = Executors.newFixedThreadPool(10);
        // 适合使用于Runnable
        service.execute(new MyRunnable());
        // 适合使用于Runnable、Callable
        service.submit(new MyRunnable());
        service.submit(new MyRunnable());
        service.shutdown();
    }
}

package com.hoshino.basics.juc;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author huangyuehao
 * @date 2023-02-15
 */
public class MyFuture {

    private static final Integer CORE_POOL_SIZE = 6;

    private static final Integer MAXIMUM_POOL_SIZE = 10;

    private static final Long KEEP_ALIVE_TIME = 10L;

    private static final ArrayBlockingQueue<Runnable> ARRAY_BLOCKING_QUEUE = new ArrayBlockingQueue<>(20, true);

    private static final ThreadFactory THREAD_FACTORY = new ThreadFactoryBuilder().build();

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.MINUTES,
                ARRAY_BLOCKING_QUEUE, THREAD_FACTORY);

        Future<?> submit = threadPoolExecutor.submit(() -> {
            System.out.println(Thread.currentThread().getName());
        });
        try {
            String result = (String) submit.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}

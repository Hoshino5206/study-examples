package com.hoshino.basics.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author Akino
 * @date 2023-05-09
 */
public class MyThreadPool {

    private static final Integer CORE_POOL_SIZE = 6;

    private static final Integer MAXIMUM_POOL_SIZE = 10;

    private static final Long KEEP_ALIVE_TIME = 10L;

    private static final TimeUnit TIME_UNIT = TimeUnit.MINUTES;

    private static final ArrayBlockingQueue<Runnable> ARRAY_BLOCKING_QUEUE = new ArrayBlockingQueue<>(20, true);

    private static final ThreadFactory THREAD_FACTORY = new ThreadFactoryBuilder().build();

    private static final ThreadPoolExecutor.AbortPolicy ABORT_POLICY = new ThreadPoolExecutor.AbortPolicy();

    private static ExecutorService executorService;

    static {
        executorService = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TIME_UNIT,
                ARRAY_BLOCKING_QUEUE, THREAD_FACTORY, ABORT_POLICY);
    }

    public static ExecutorService getExecutorService() {
        return executorService;
    }

    public static void invoke(Runnable runnable) {
//        // Executors工具类创建线程池
//        ExecutorService service = Executors.newFixedThreadPool(10);
//        // service.execute()适合使用于Runnable，service.submit()适合使用于Runnable、Callable
//        service.submit(runnable);
//        service.shutdown();

        getExecutorService().submit(runnable);
        getExecutorService().shutdown();
    }
}

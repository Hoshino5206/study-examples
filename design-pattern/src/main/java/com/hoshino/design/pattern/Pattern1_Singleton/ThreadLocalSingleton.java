package com.hoshino.design.pattern.Pattern1_Singleton;

import java.util.concurrent.*;

/**
 * @author huangyuehao
 * @date 2023-03-07
 * ThreadLocal单例创建单例模式：不保证整个应用全局唯一，但保证线程内部全局唯一，以空间换时间，且线程安全。
 */
public class ThreadLocalSingleton {

    private ThreadLocalSingleton(){}

    private static final Integer CORE_POOL_SIZE = 6;

    private static final Integer MAXIMUM_POOL_SIZE = 10;

    private static final Long KEEP_ALIVE_TIME = 10L;

    private static final ArrayBlockingQueue<Runnable> ARRAY_BLOCKING_QUEUE = new ArrayBlockingQueue<>(20, true);

    private static final ThreadFactory THREAD_FACTORY = Executors.defaultThreadFactory();

    private static final ThreadPoolExecutor.AbortPolicy ABORT_POLICY = new ThreadPoolExecutor.AbortPolicy();

    private static final ThreadLocal<ThreadLocalSingleton> THREAD_LOCAL_INSTANCE = ThreadLocal.withInitial(() -> new ThreadLocalSingleton());

    public static ThreadLocalSingleton getInstance() {
        return THREAD_LOCAL_INSTANCE.get();
    }

    public static void main(String[] args) {
        ExecutorService executor = new ThreadPoolExecutor(
                CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.MINUTES,
                ARRAY_BLOCKING_QUEUE, THREAD_FACTORY, ABORT_POLICY);

        executor.submit(() ->{
            System.out.println(Thread.currentThread().getName() + "-----" + ThreadLocalSingleton.getInstance());
            System.out.println(Thread.currentThread().getName() + "-----" + ThreadLocalSingleton.getInstance());
        });

        executor.execute(() -> {
            System.out.println(Thread.currentThread().getName() + "-----" + ThreadLocalSingleton.getInstance());
            System.out.println(Thread.currentThread().getName() + "-----" + ThreadLocalSingleton.getInstance());
        });

        executor.shutdown();
    }

    /**
     * pool-1-thread-1-----com.hoshino.design.pattern.Pattern1_Singleton.ThreadLocalSingleton@46ebab1a
     * pool-1-thread-1-----com.hoshino.design.pattern.Pattern1_Singleton.ThreadLocalSingleton@46ebab1a
     * pool-1-thread-2-----com.hoshino.design.pattern.Pattern1_Singleton.ThreadLocalSingleton@251b4afc
     * pool-1-thread-2-----com.hoshino.design.pattern.Pattern1_Singleton.ThreadLocalSingleton@251b4afc
     */

}
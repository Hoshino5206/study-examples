package com.hoshino.basics.juc;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author huangyuehao
 * @date 2023-02-17
 */
public class MyCyclicBarrier {

    private static final Integer CORE_POOL_SIZE = 6;

    private static final Integer MAXIMUM_POOL_SIZE = 10;

    private static final Long KEEP_ALIVE_TIME = 10L;

    private static final ArrayBlockingQueue<Runnable> ARRAY_BLOCKING_QUEUE = new ArrayBlockingQueue<>(20, true);

    private static final ThreadFactory THREAD_FACTORY = new ThreadFactoryBuilder().build();

    private static final Integer CYCLIC_BARRIER = 6;

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(CYCLIC_BARRIER);

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) {
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(
                CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.MINUTES,
                ARRAY_BLOCKING_QUEUE, THREAD_FACTORY);

        for (int i = 0; i < CYCLIC_BARRIER; i++) {
            executorService.submit(() -> {
                try {
                    int randomNum = new Random().nextInt((3000 - 1000) + 1) + 1000;//产生1000到3000之间的随机整数
                    Thread.sleep(randomNum);
                    int get = atomicInteger.incrementAndGet();
                    System.out.println(Thread.currentThread().getName() + ", 通过了第" + get + "个障碍物, 使用了 " + ((double) randomNum / 1000) + "s");
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
        System.out.println(Thread.currentThread().getName() + "已完成");

        executorService.shutdown();
    }

}

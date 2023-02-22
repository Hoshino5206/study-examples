package com.hoshino.basics.juc;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author huangyuehao
 * @date 2023-02-16
 */
public class MyCountDownLatch {

    private static final Integer CORE_POOL_SIZE = 6;

    private static final Integer MAXIMUM_POOL_SIZE = 10;

    private static final Long KEEP_ALIVE_TIME = 10L;

    private static final ArrayBlockingQueue<Runnable> ARRAY_BLOCKING_QUEUE = new ArrayBlockingQueue<>(20, true);

    private static final ThreadFactory THREAD_FACTORY = new ThreadFactoryBuilder().build();

    private static final ThreadPoolExecutor.AbortPolicy ABORT_POLICY = new ThreadPoolExecutor.AbortPolicy();

    private static final Integer COUNT_DOWN_LATCH = 6;

    private static CountDownLatch countDownLatch = new CountDownLatch(COUNT_DOWN_LATCH);

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(
                CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.MINUTES,
                ARRAY_BLOCKING_QUEUE, THREAD_FACTORY, ABORT_POLICY);

        for (int i = 0; i < COUNT_DOWN_LATCH; i++) {
            executorService.submit(() -> {
                try {
                    //产生1000到3000之间的随机整数
                    int randomNum = new Random().nextInt((3000 - 1000) + 1) + 1000;
                    Thread.sleep(randomNum);
                    System.out.println(Thread.currentThread().getName()+" 已经准备好了, 所使用的时间为 "+((double)randomNum/1000)+"s");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        System.out.println("正在等待所有玩家准备好");
        countDownLatch.await();
        System.out.println("开始游戏");
        executorService.shutdown();
    }

}

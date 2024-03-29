package com.hoshino.basics.juc;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.hoshino.basics.thread.MyThreadPool;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author huangyuehao
 * @date 2023-02-16
 */
public class MyCountDownLatch {

    private static final Integer COUNT_DOWN_LATCH = 6;

    private static CountDownLatch countDownLatch = new CountDownLatch(COUNT_DOWN_LATCH);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = MyThreadPool.getExecutorService();

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

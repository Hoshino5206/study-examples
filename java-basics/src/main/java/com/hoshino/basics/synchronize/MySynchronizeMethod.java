package com.hoshino.basics.synchronize;

import com.hoshino.basics.thread.MyThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * synchronized同步方法
 * @author Akino
 * @date 2023-08-08
 */
public class MySynchronizeMethod implements Runnable {

    private static int ticket = 100;

    @Override
    public void run() {
        while (true) {
            sellTicket();
        }
    }

    /**
     * 锁对象是谁调用这个方法就是谁，隐含锁对象就是this
     */
    public synchronized void sellTicket() {
        if (ticket > 0) {
            // 出票操作，使用sleep模拟一下出票时间
            try {
                Thread.sleep(100);
                ticket--;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "正在卖票，剩余票数:" + ticket);
        }
    }

    public static void main(String[] args) {
        MySynchronizeMethod runnable = new MySynchronizeMethod();
        new Thread(runnable).start();
    }
}

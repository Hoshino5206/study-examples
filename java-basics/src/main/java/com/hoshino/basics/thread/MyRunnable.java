package com.hoshino.basics.thread;

/**
 * @author Akino
 * @date 2023-05-09
 */
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " 正在运行......");
    }
}

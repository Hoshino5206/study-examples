package com.hoshino.basics.thread;

/**
 * @author Akino
 * @date 2023-05-09
 */
public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " 正在运行......");
    }
}

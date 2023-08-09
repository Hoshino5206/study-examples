package com.hoshino.basics.thread;

import java.util.concurrent.ExecutorService;

/**
 * @author Akino
 * @date 2023-05-09
 */
public class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": MyThread 正在运行......");
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
    }
}

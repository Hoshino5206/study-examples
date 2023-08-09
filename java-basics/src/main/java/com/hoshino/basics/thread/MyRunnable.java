package com.hoshino.basics.thread;

/**
 * @author Akino
 * @date 2023-05-09
 */
public class MyRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": MyRunnable 正在运行......");
    }

    public static void main (String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        thread.start();
    }
}

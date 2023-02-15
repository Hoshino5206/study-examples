package com.hoshino.basics.thread;

/**
 * @author huangyuehao
 * @date 2023-02-15
 */
public class MyRunnable {

    public static void main (String[] args) {
        RunnableDemo runnableDemo = new RunnableDemo();
        Thread thread = new Thread(runnableDemo);
        thread.start();
    }

}

class RunnableDemo implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " 正在运行......");
    }

}



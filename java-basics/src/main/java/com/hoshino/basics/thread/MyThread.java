package com.hoshino.basics.thread;

/**
 * @author huangyuehao
 * @date 2023-02-15
 */
public class MyThread {

    public static void main(String[] args) {
        ThreadDemo threadDemo = new ThreadDemo();
        threadDemo.start();
    }

}

class ThreadDemo extends Thread {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " 正在运行......");
    }

}



package com.hoshino.basics.thread;

/**
 * @author huangyuehao
 * @date 2023-02-15
 */
public class RunnableTest {
    public static void main (String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        thread.start();
    }
}



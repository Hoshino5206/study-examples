package com.hoshino.basics.synchronize;

/**
 * 死锁例子
 * @author Akino
 * @date 2023-08-08
 */
public class MyDeadLock {

    private StringBuffer s1 = new StringBuffer();

    private StringBuffer s2 = new StringBuffer();

    public void run() {
        new Thread(() -> {
            synchronized (s1) {
                s1.append("a");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (s2) {
                    s2.append("b");
                }
                System.out.println("s1 = " + s1);
                System.out.println("s2 = " + s2);
            }
        }).start();

        new Thread(() -> {
            synchronized (s2) {
                s1.append("c");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (s1) {
                    s2.append("d");
                }
                System.out.println("s1 = " + s1);
                System.out.println("s2 = " + s2);
            }
        }).start();
    }

    public static void main(String[] args) {
        MyDeadLock myDeadLock = new MyDeadLock();
        myDeadLock.run();
    }
}

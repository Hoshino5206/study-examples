package com.hoshino.basics.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁 ReentrantLock 的底层是通过 AQS 实现，支持公平锁和非公平锁
 * @author Akino
 * @date 2023-08-08
 */
public class MyReentrantLock {

    private Lock lock = new ReentrantLock();

    public void invoke() {
        new Thread(() -> {
            try {
                if (lock.tryLock()) {
                    lock.lock();
                    System.out.println("第1次获取锁，这个锁是：" + lock);
                    for (int i = 2; i <= 10; i++) {
                        try {
                            lock.lock();
                            System.out.println("第" + i + "次获取锁，这个锁是：" + lock);
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            // 如果把这里注释掉的话，那么程序就会陷入死锁当中。
                            lock.unlock();
                        }
                    }
                }
            } finally {
                lock.unlock();
            }
        }).start();
    }

    public static void main(String[] args) {
        MyReentrantLock lock = new MyReentrantLock();
        lock.invoke();
    }
}

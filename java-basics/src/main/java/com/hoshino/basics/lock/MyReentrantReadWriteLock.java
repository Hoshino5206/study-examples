package com.hoshino.basics.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁，适合读多写少的场景
 * @author Akino
 * @date 2023-08-08
 */
public class MyReentrantReadWriteLock {

    private ReadWriteLock rwLock = new ReentrantReadWriteLock();

    private volatile Map<String, Object> map = new HashMap<>();

    /**
     * 定义写操作
     * 满足：原子 + 独占
     */
    public void put(String key, Object value) {
        rwLock.writeLock().lock();
        System.out.println(Thread.currentThread().getName() + "\t 正在写入：" + key);
        try {
            // 模拟网络拥堵，延迟0.3秒
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        map.put(key, value);
        System.out.println(Thread.currentThread().getName() + "\t 写入完成");
        rwLock.writeLock().unlock();
    }

    public void get(String key) {
        rwLock.readLock().lock();
        System.out.println(Thread.currentThread().getName() + "\t 正在读取...................");
        try {
            // 模拟网络拥堵，延迟0.3秒
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Object value = map.get(key);
        System.out.println(Thread.currentThread().getName() + "\t 读取完成：" + value);
        rwLock.readLock().unlock();
    }

    public static void main(String[] args) {
        MyReentrantReadWriteLock myCache = new MyReentrantReadWriteLock();
        // 线程操作资源类，5个线程写
        for (int i = 1; i <= 5; i++) {
            // lambda表达式内部必须是final
            final int tempInt = i;
            new Thread(() -> {
                myCache.put(tempInt + "", tempInt + "");
            }, "写线程" + i + ":").start();
        }
        // 线程操作资源类， 5个线程读
        for (int i = 1; i <= 5; i++) {
            // lambda表达式内部必须是final
            final int tempInt = i;
            new Thread(() -> {
                myCache.get(tempInt + "");
            }, "读线程" + i + ":").start();
        }
    }
}

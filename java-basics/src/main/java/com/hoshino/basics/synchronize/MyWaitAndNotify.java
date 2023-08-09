package com.hoshino.basics.synchronize;

/**
 * notify() 和 notifyAll()，需要配合 synchronize 关键字使用
 * @author Akino
 * @date 2023-08-08
 */
public class MyWaitAndNotify {

    private Object obj = new Object();

    public void invoke() {
        // 1.开启一个子线程进入无限等待状态，若没有被唤醒则无法继续运行
        new Thread(() -> {
            try {
                synchronized (obj) {
                    // wait() 方法必须配合 synchronize 一起使用，否则抛异常 InterruptedException
                    // wait() 状态的线程能够被 notify() 和 notifyAll() 线程唤醒，而 sleep() 状态的线程不能被 notify() 方法唤醒；
                    // wait() 方法会释放对象锁，但是 sleep() 方法不会
                    // wait() 方法是实例方法，不能在 static 中使用
                    System.out.println(Thread.currentThread().getName() + ":线程1 begin wait ....");
                    obj.wait();
                    System.out.println(Thread.currentThread().getName() + ":线程1 over ....");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        // 2.线程休眠3秒
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 3.notify() 随机唤醒一个 wait 线程，也可以使用 notifyAll() 方法唤醒所有 wait 线程
        new Thread(() -> {
            try {
                synchronized (obj) {
                    System.out.println(Thread.currentThread().getName() + ":线程2 begin wait ....");
                    System.out.println(Thread.currentThread().getName() + ":唤醒线程1，线程1进入就绪状态，等待cpu调度 ....");
                    obj.notify();
                    System.out.println(Thread.currentThread().getName() + ":线程2 over ....");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        MyWaitAndNotify myWaitAndNotify = new MyWaitAndNotify();
        myWaitAndNotify.invoke();
    }
}

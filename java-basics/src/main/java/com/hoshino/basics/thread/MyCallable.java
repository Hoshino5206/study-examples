package com.hoshino.basics.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

/**
 * @author huangyuehao
 * @date 2023-02-15
 */
public class MyCallable {

    public static void main(String[] args) {
        CallableDemo callableDemo = new CallableDemo();
        // 1.执行 Callable 方式，需要 FutureTask 实现类的支持，用于接收运算结果。
        RunnableFuture<Integer> result = new FutureTask<>(callableDemo);
        Thread thread = new Thread(result);
        thread.start();
        System.out.println("------------------------------------");
        // 2.接收线程运算后的结果
        try {
            // FutureTask可用于闭锁，类似于CountDownLatch的作用，在所有的线程没有执行完成之后这里是不会执行的
            Integer sum = result.get();
            System.out.println("sum = 100000! = " + sum);
            System.out.println("------------------------------------");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}

class CallableDemo implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int sum = 0;

        for (int i = 0; i <= 100000; i++) {
            sum += i;
        }
        return sum;
    }

}

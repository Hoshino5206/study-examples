package com.hoshino.basics.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author Akino
 * @date 2023-05-09
 */
public class MyCallable implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + ": MyCallable 正在运行......");
        int sum = 0;
        for (int i = 0; i <= 100; i++) {
            Thread.sleep(20);
            sum += i;
        }
        return sum;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCallable myCallable = new MyCallable();
        FutureTask futureTask = new FutureTask(myCallable);
        Thread thread = new Thread(futureTask);
        thread.start();

        Object result = futureTask.get();
        System.out.println(">>>>>>>>> result = " + result);

    }
}

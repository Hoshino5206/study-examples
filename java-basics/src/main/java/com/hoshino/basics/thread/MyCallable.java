package com.hoshino.basics.thread;

import java.util.concurrent.Callable;

/**
 * @author Akino
 * @date 2023-05-09
 */
public class MyCallable implements Callable {
    @Override
    public Integer call() throws Exception {
        int sum = 0;

        for (int i = 0; i <= 100000; i++) {
            sum += i;
        }
        return sum;
    }
}

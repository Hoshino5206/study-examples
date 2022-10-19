package com.hoshino.study.maven.jdk8;

import java.util.Random;
import java.util.function.Supplier;

/**
 * @author huangyuehao•
 * @date 2022-08-24
 */
public class MySupplier {

    public static void main (String[] args) {
        Supplier<Integer> supplier = new Supplier<Integer>() {
            @Override
            public Integer get() {
                //返回一个随机值
                return new Random().nextInt();
            }
        };

        for (int i = 0; i < 5; i++) {
            Integer integer = supplier.get();
            System.out.println(integer);
        }

    }
}

package com.hoshino.study.maven.jdk8;

import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * consumer接口就是一个消费型的接口，通过传入参数，然后输出值。
 *
 * @author huangyuehao
 * @date 2022-08-24
 */
public class MyConsumer {

    public static void main (String[] args) {
        // 1、使用consumer接口实现方法
        Consumer<String> consumer = new Consumer<String>() {

            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        Stream<String> stream = Stream.of("aaa", "bbb", "ddd", "ccc", "fff");
        stream.forEach(consumer);
        System.out.println("********************");


        // 2、使用lambda表达式，forEach方法需要的就是一个Consumer接口
        Consumer<String> consumer2 = (s) -> System.out.println(s);//lambda表达式返回的就是一个Consumer接口
        Stream<String> stream2 = Stream.of("aaa", "bbb", "ddd", "ccc", "fff");
        stream2.forEach(consumer2);
        // 更直接的方式 stream2.forEach((s) -> System.out.println(s));
        System.out.println("********************");

        // ③ 使用方法引用，方法引用也是一个consumer
        Consumer<String> consumer3 = System.out::println;
        Stream<String> stream3 = Stream.of("aaa", "bbb", "ddd", "ccc", "fff");
        stream3.forEach(consumer3);
        // 更直接的方式 stream.forEach(System.out::println);

    }

}

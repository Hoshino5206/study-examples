package com.hoshino.example.maven.jdk8;

import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Supplier 接口是一个供给型的接口，其实就是一个容器，可以用来存储数据，然后可以供其他方法使用的这么一个接口。
 * @author huangyuehao•
 * @date 2022-08-24
 */
public class MySupplier {

    public static void main (String[] args) {
        // 1、使用Supplier接口实现方法,只有一个get方法，无参数，返回一个值
        Supplier<Integer> supplier1 = new Supplier<Integer>() {
            @Override
            public Integer get() {
                //返回一个随机值
                return new Random().nextInt();
            }
        };
        System.out.println(supplier1.get());
        System.out.println("********************");

        // 2、使用lambda表达式，
        Supplier<Integer> supplier2 = () -> new Random().nextInt();
        System.out.println(supplier2.get());
        System.out.println("********************");

        // 3、使用方法引用
        Supplier<Double> supplier3 = Math::random;
        System.out.println(supplier3.get());
        System.out.println("********************");

        // 4、配置optional使用
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);
        //返回一个optional对象
        Optional<Integer> first = stream.filter(i -> i > 4)
                .findFirst();
        //optional对象有需要Supplier接口的方法
        //orElse，如果first中存在数，就返回这个数，如果不存在，就放回传入的数
        System.out.println(first.orElse(1));
        System.out.println(first.orElse(7));
        System.out.println("********************");
        Supplier<Integer> supplier = new Supplier<Integer>() {
            @Override
            public Integer get() {
                //返回一个随机值
                return new Random().nextInt();
            }
        };
        //orElseGet，如果first中存在数，就返回这个数，如果不存在，就返回supplier返回的值
        System.out.println(first.orElseGet(supplier));

        // 更直接的方式
        System.out.println("first.orElseGet(() -> {\n            return new Random().nextInt();\n        }) = " + first.orElseGet(() -> {
            return new Random().nextInt();
        }));
    }

}

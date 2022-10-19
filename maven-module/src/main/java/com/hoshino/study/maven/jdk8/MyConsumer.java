package com.hoshino.study.maven.jdk8;

import java.util.stream.Stream;

/**
 * @author huangyuehao
 * @date 2022-08-24
 */
public class MyConsumer {

    public static void main (String[] args) {
        Stream<String> stream = Stream.of("aaa", "bbb", "ddd", "ccc", "fff");
        stream.forEach(s -> System.out.println(s));
        System.out.println("********************");
    }

}

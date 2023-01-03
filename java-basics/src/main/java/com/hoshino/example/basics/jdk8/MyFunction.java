package com.hoshino.example.basics.jdk8;

import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Function 接口是一个功能型接口，它的一个作用就是转换作用，将输入数据转换成另一种形式的输出数据。
 * @author huangyuehao
 * @date 2022-10-21
 */
public class MyFunction {

    public static void main (String[] args) {
        // 1、使用map方法，泛型的第一个参数是转换前的类型，第二个是转化后的类型
        Function<String, Integer> function = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return s.length();//获取每个字符串的长度，并且返回
            }
        };

        Stream<Integer> stream = Stream.of("aaa", "bbbbb", "ccccccc")
                .map(function);
        stream.forEach(System.out::println);
        System.out.println("********************");

    }

}

package com.hoshino.basics.jdk8;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Predicate 接口是一个谓词型接口，这个就是一个类似于 bool 类型的判断的接口。
 * @author huangyuehao
 * @date 2022-10-21
 */
public class MyPredicate {

    public static void main (String[] args) {
        // 1、使用Predicate接口实现方法,只有一个test方法，传入一个参数，返回一个bool值
        Predicate<Integer> predicate1 = new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                if(integer > 5){
                    return true;
                }
                return false;
            }
        };
        System.out.println(predicate1.test(6));
        System.out.println("********************");

        // 2、使用lambda表达式，
        Predicate<Integer> predicate2 = (t) -> t > 5;
        System.out.println(predicate2.test(1));
        System.out.println("********************");

        // 3、将Predicate作为filter接口，Predicate起到一个判断的作用
        Predicate<Integer> predicate3 = new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                if(integer > 5){
                    return true;
                }
                return false;
            }
        };
        List<Integer> list = Stream.of(1, 23, 3, 4, 5, 56, 6, 6)
                .filter(predicate3)
//                .filter(i -> i > 5)
                .collect(Collectors.toList());
        list.forEach(System.out::println);
        System.out.println("********************");

    }

}

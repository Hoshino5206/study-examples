package com.hoshino.example.basics.jdk8;

import com.hoshino.example.basics.domain.Users;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author huangyuehao
 * @date 2022-07-22
 */
public class MyStream {
    public static void main (String[] args) {
        int[] arr = {1, 2, 3};

        Users users1 = new Users(1, "tom", "200", 20);
        Users users2 = new Users(2, "zhangsan", "300", 30);
        Users users3 = new Users(3, "lisi", "400", 40);
        Users users4 = new Users(3, "lucy", "500", 30);
        Users users5 = null;

        List<Integer> collectFilter = Stream.of(users1, users2, users3, users4, users5)
                // 过滤null对象
                .filter(users -> users != null)
                // 取出age字段
                .map(Users::getAge)
                // 按大小排序
                .sorted(Integer::compareTo)
                // 跳过前n条数据
                .skip(1)
                // 去重复
                .distinct()
                // 对元素进行遍历处理
                .peek(i -> System.out.println("i = " + i))
                // 截取排在前n位的元素
                .limit(2)
                .collect(Collectors.toList());
        System.out.println("collectFilter = " + collectFilter);

        // 返回元素个数
        long count = Stream.of(1, 2, 5, 7, 8, 12, 33).count();
        System.out.println("count = " + count);

        // reduce作用是每次计算的时候都用到上一次的计算结果
        Integer reduce = Stream.of(1, 2, 5, 7, 8, 12, 33)
                .reduce(0, (x, y) -> x + y);
        System.out.println("reduce = " + reduce);

        // findFirst获取 Stream 中的第一个元素。
        Optional<Integer> first = Stream.of(100, 2, 5, 7, 8, 12, 33)
                .findFirst();
        System.out.println("first = " + first.get());

        // findAny 获取 Stream 中的某个元素, 串行并行结果不一样
        Stream.of(100, 2, 5, 7, 8, 12, 33)
                .parallel()
                .findAny()
                .ifPresent(findAny -> System.out.println("findAny = " + findAny));

        // anyMatch匹配上任何一个则返回 Boolean
        boolean anyMatch = Stream.of(1, 2, 5, 7, 8, 12, 33)
                .anyMatch(integer -> integer == 12);
        System.out.println("anyMatch = " + anyMatch);

        // allMatch 匹配所有的元素则返回 Boolean
        boolean allMatch = Stream.of(1, 2, 5, 7, 8, 12, 33)
                .allMatch(integer -> integer == 1);
        System.out.println("allMatch = " + allMatch);

    }
}
package com.hoshino.basics.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * @author huangyuehao
 * @date 2022-08-30
 */
public class MyTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList("0", "66", "22", "11", "22", "33"));
        // 使用增强for本质上也是Iterator
        for (String i : list) {
            if ("22".equals(i)) {
                list.remove(i);
            }
        }
        System.out.println(list);

        List<String> list1 = new ArrayList<>(Arrays.asList("0", "11", "22", "22", "33", "22", "23", "55"));
        // ArrayList的forEach源码中会检测modCount == expectedModCount
        list1.forEach(i -> {
            if ("22".equals(i)) {
                list1.remove(i);
            }
        });
        System.out.println("list1 = " + list1);

        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
        // ArrayList/LinkedList/Vector
        // HashSet/LinkedHashSet/TreeSet
    }

}

package com.hoshino.basics.collection;

import java.util.LinkedHashSet;

/**
 * @author huangyuehao
 * @date 2023-01-29
 */
public class MyLinkedHashSet {

    public static void main (String[] args) {
        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        set.add(123);
        set.add(456);
        set.add(789);
        set.add(789);
        set.forEach(System.out::println);
    }

}

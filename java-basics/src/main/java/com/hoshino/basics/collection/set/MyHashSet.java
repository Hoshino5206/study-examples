package com.hoshino.basics.collection.set;

import java.util.HashSet;

/**
 * @author huangyuehao
 * @date 2023-01-29
 */
public class MyHashSet {

    public static void main(String[] args) {
        HashSet<Integer> set = new HashSet<>();
        set.add(1111);
        set.add(2222);
        set.add(3333);
        set.add(3333);
        set.forEach(System.out::println);
    }

}

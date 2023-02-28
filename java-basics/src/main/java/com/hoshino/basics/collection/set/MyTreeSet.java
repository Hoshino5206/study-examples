package com.hoshino.basics.collection.set;

import java.util.TreeSet;

/**
 * @author huangyuehao
 * @date 2023-01-29
 */
public class MyTreeSet {

    public static void main(String[] args) {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(23);
        set.add(221);
        set.add(12);
        set.add(97);
        set.add(55);
        set.add(12);
        set.forEach(System.out::println);
    }

}

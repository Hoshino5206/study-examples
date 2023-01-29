package com.hoshino.basics.collection;

import java.util.ArrayList;

/**
 * @author huangyuehao
 * @date 2023-01-28
 */
public class MyArrayList {

    public static void main (String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("hello, ");
        list.add("world !");
        list.forEach(System.out::print);
    }

}

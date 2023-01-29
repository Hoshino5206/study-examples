package com.hoshino.basics.collection;

import java.util.Vector;

/**
 * @author huangyuehao
 * @date 2023-01-29
 */
public class MyVector {

    public static void main (String[] args) {
        Vector<String> vector = new Vector<>();
        vector.add("hello, ");
        vector.add("world ");
        vector.add("!!!");
        vector.forEach(System.out::print);
    }

}

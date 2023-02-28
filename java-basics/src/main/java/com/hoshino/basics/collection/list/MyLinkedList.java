package com.hoshino.basics.collection.list;

import java.util.LinkedList;

/**
 * @author huangyuehao
 * @date 2023-01-29
 */
public class MyLinkedList {

    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
        list.add(", ");
        list.add("world ");
        list.addFirst("hello ");
        list.addLast("!!!");
        list.forEach(System.out::print);
    }

}

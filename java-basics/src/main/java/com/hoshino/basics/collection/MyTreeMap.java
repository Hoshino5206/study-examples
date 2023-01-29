package com.hoshino.basics.collection;

import java.util.*;

/**
 * @author huangyuehao
 * @date 2023-01-29
 */
public class MyTreeMap {

    public static void main (String[] args) {
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(3, "Java");
        map.put(2, "Spring");
        map.put(1, "SpringBoot");
        map.put(4, "SpringCloud");

        Set<Integer> keys = map.keySet();
        System.out.println("keys = " + keys);

        Collection<String> values = map.values();
        System.out.println("values = " + values);

        Set<Map.Entry<Integer, String>> entries = map.entrySet();
        System.out.println("entries = " + entries);
        for (Map.Entry<Integer, String> entry : entries) {
            Integer key = entry.getKey();
            String value = entry.getValue();
            System.out.println("key = " + key + ", value = " + value);
        }
    }

}

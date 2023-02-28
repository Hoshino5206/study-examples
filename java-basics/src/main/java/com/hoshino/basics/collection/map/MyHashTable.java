package com.hoshino.basics.collection.map;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

/**
 * @author huangyuehao
 * @date 2023-01-29
 */
public class MyHashTable {

    public static void main(String[] args) {
        Hashtable<Integer, String> map = new Hashtable<>();
        map.put(1, "C++");
        map.put(2, "Java");
        map.put(3, "Python");

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

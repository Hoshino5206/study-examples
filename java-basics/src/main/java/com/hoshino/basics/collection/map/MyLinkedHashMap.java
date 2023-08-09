package com.hoshino.basics.collection.map;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author huangyuehao
 * @date 2023-02-28
 */
public class MyLinkedHashMap {

    public static void main(String[] args) {
        LinkedHashMap<Integer, String> map = new LinkedHashMap<>();
        map.put(1, "JavaSE");
        map.put(2, "SpringMVC");
        map.put(3, "SpringCloud");

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

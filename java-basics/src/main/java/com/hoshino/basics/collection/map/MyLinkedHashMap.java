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
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("Java", "JavaSE");
        map.put("Spring", "SpringMVC");
        map.put("SpringBoot", "SpringCloud");

        Set<String> keys = map.keySet();
        System.out.println("keys = " + keys);

        Collection<String> values = map.values();
        System.out.println("values = " + values);

        Set<Map.Entry<String, String>> entries = map.entrySet();
        System.out.println("entries = " + entries);
        for (Map.Entry<String, String> entry : entries) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println("key = " + key + ", value = " + value);
        }
    }

}

package com.hoshino.algorithm.HashMap遍历方法;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Yy_hoshino
 * @date 2021-09-18 19:13
 */
public class Iter_HashMap {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(1, "a");
        map.put(2, "b");
        map.put(3, "C");

        System.out.println("使用EntrySet 方法遍历：");
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }

        System.out.println("使用KeySet方法遍历");
        Set<Integer> keys = map.keySet();
        for (Integer key : keys) {
            System.out.println(key + " = " + map.get(key));
        }

    }
}

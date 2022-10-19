package com.hoshino.study.maven.utils;

/**
 * @author huangyuehao
 * @date 2022-08-30
 */
public class MyTest {

    private static void testString(String[] strings) {
        strings[0] = "lol";
    }

    public static void main (String[] args) {
        String[] str =  new String[] {"ab", "cd", "efg"};
        testString(str);
        System.out.println("-------");
        System.out.println(str[0]);
    }
}

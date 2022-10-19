package com.hoshino.study.maven.utils;

/**
 * @author huangyuehao
 * @date 2022-08-23
 */
public class MyInteger {
    public static void main (String[] args) {
        // Integer缓存机制 -128到127
        Integer a = new Integer(1);
        Integer b = new Integer(1);
        System.out.println(a == b);//false
        Integer a1 = 1;
        Integer b1 = 1;
        System.out.println(a1 == b1);//true
        Integer a2 = 200;
        Integer b2 = 200;
        System.out.println(a2 == b2);//false

        // 装箱和拆箱
    }
}

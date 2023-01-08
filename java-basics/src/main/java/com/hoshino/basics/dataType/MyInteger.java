package com.hoshino.basics.dataType;

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

        // 手动装箱
        int num1 = 100;
        Integer i1 = new Integer(num1);
        Integer i2 = Integer.valueOf(num1);
        // 手动拆箱
        int i3 = i1.intValue();
        int i4 = i2.intValue();

        // 自动装箱
        Integer autoPack = 10;
        // 自动拆箱
        int autoUnPack = new Integer(10);
    }
}

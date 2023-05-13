package com.hoshino.basics.dataType;

/**
 * 装箱缓存，在通过valueof方法创建Integer对象的时候，如果数值在[-128,127]之间，
 * 便返回指向IntegerCache.cache(缓存池)中已经存在的对象的引用，否则创建一个新的Integer对象。
 * @author huangyuehao
 * @date 2022-08-23
 */
public class MyInteger {
    public static void main (String[] args) {
        // Integer缓存机制 -128到127
        Integer a = new Integer(1);
        Integer b = new Integer(1);
        //false
        System.out.println(a == b);

        Integer a1 = 1;
        Integer b1 = 1;
        //true
        System.out.println(a1 == b1);

        Integer a2 = 200;
        Integer b2 = 200;
        //false
        System.out.println(a2 == b2);

        // 手动装箱
        int num1 = 100;
        Integer i1 = new Integer(num1);
        Integer i2 = Integer.valueOf(num1);
        // 自动装箱
        Integer autoPack = 10;

        // 手动拆箱
        int i3 = i1.intValue();
        int i4 = i2.intValue();
        // 自动拆箱
        int autoUnPack = new Integer(10);
    }
}

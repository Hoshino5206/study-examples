package com.hoshino.basics.classload;

/**
 * @author huangyuehao
 * @date 2022-11-23
 */
public class MyParentClassLoad {

    static int a = 10;

    static {
        System.out.println("parent class static module, a = " + a);
    }

    static int b = 20;

    static {
        System.out.println("parent class static module, a = " + a + ", b = " + b);
    }

    public static void staticOut() {
        System.out.println("static method(out), a = " + a + ", b = " + b);
    }

    public void out() {
        System.out.println("not static method(out), a = " + a + ", b = " + b);
    }

}

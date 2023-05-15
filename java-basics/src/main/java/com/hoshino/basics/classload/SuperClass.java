package com.hoshino.basics.classload;

/**
 * @author huangyuehao
 * @date 2022-11-23
 */
public class SuperClass {

    static int a = 10;

    static {
        System.out.println("父类静态代码块");
        System.out.println("父类静态变量，a = " + a);
    }

    {
        System.out.println("父类构造代码块");
    }

    public SuperClass() {
        System.out.println("父类构造函数");
    }

}

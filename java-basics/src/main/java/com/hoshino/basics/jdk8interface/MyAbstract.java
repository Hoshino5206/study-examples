package com.hoshino.basics.jdk8interface;

/**
 * @author Akino
 * @date 2023-05-09
 */
public abstract class MyAbstract {

    public int i;

    public MyAbstract() {
        i = 10;
        System.out.println("抽象类的无参构造函数");
    }

    /**
     * 与接口作用类似
     */
    abstract void testAbstract1();

    void testAbstract2() {};
}

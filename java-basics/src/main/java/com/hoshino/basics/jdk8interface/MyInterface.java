package com.hoshino.basics.jdk8interface;

/**
 * JDK1.8接口新特性
 * @author Akino
 * @date 2023-05-09
 */
public interface MyInterface {
    /**
     * 接口可以定义变量，但变量必须是 public static final
     */
    public int ABC = 2;

    public static final int ROOT = 6;

    /**
     * 接口也可以实现方法，使用关键字 default static
     */
    default void fly() {
        System.out.println("慢慢飞");
    }

    static void run() {
        System.out.println("慢慢跑");
    }
}

package com.hoshino.basics.classload;

/**
 * @author huangyuehao
 * @date 2022-11-23
 * 1.类的加载顺序：静态代码块>构造代码块>构造函数>普通代码块
 * 2.父子类的加载顺序
 * 3.内部类的加载顺序：内部类是延时加载的，也就是说只会在第一次使用时加载，不使用就不加载。由此，可以很好的用于单例模式
 * （不使用不加载：避免了饿汉式的内存浪费；可巧妙避免线程安全问题）
 */
public class SubClass extends SuperClass {

    static int b = 30;

    static{
        System.out.println("子类静态代码块");
        System.out.println("子类静态变量，b = " + b);
    }

    {
        System.out.println("子类构造代码块");
    }

    public SubClass(){
        System.out.println("子类构造函数");
    }

    public static void main(String[] args) {
        SubClass subClass = new SubClass();
        System.out.println("--------------------------------");
        SubClass subClass1 = new SubClass();
    }

}

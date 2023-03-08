package com.hoshino.design.pattern.Pattern1_Singleton;

/**
 * @author huangyuehao
 * @date 2023-03-06
 * 饿汉式创建单例对象：类加载时就初始化，线程安全
 */
public class SingletonHungry {

    private SingletonHungry() {}

    private static final SingletonHungry SINGLETON = new SingletonHungry();

    public static SingletonHungry getSingleton() {
        return SINGLETON;
    }

}

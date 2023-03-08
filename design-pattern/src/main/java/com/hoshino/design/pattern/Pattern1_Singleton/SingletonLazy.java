package com.hoshino.design.pattern.Pattern1_Singleton;

/**
 * @author huangyuehao
 * @date 2023-03-06
 * 懒汉式创建单例模式：第一次调用才初始化，避免内存浪费。
 * 1.由于懒汉式是非线程安全，所以加上线程锁保证线程安全
 * 2.双重检查锁定，在getInstance中做了两次null检查，确保了只有第一次调用单例的时候才会做同步，这样也是线程安全的，同时避免了每次都同步的性能损耗
 */
public class SingletonLazy {

    private SingletonLazy() {}

    private static SingletonLazy singleton = null;

//    public static SingletonLazy getInstance() {
//        if (singleton == null) {
//            singleton = new SingletonLazy();
//        }
//        return singleton;
//    }

//    public static synchronized SingletonLazy getInstance() {
//        if (singleton == null) {
//            singleton = new SingletonLazy();
//        }
//        return singleton;
//    }

    public static SingletonLazy getInstance() {
        if (singleton == null) {
            synchronized (SingletonLazy.class) {
                if (singleton == null) {
                    singleton = new SingletonLazy();
                }
            }
        }
        return singleton;
    }

    // 静态内部类，既实现了线程安全，又避免了同步带来的性能影响。
//    private static class LazyHolder {
//        private static final SingletonLazy INSTANCE = new SingletonLazy();
//    }
//
//    public static final SingletonLazy getInstance() {
//        return LazyHolder.INSTANCE;
//    }

}

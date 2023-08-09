package com.hoshino.basics.innerClass;

/**
 * 匿名内部类
 * @author Akino
 * @date 2023-08-08
 */
public interface MyAnonymousInnerClass {

    void run();

    public static void main(String[] args) {
        MyAnonymousInnerClass animal = new MyAnonymousInnerClass() {
            @Override
            public void run() {
                System.out.println("》》》》 Running......");
            }
        };
        animal.run();
    }

}

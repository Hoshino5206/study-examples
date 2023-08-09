package com.hoshino.basics.innerClass;

/**
 * 局部内部类
 *
 * @author Akino
 * @date 2023-08-08
 */
public class MyMethodInnerClass {

    private String name = "张三";

    public void method() {
        /**
         * 局部内部类就像是方法里面的一个局部变量一样，是不能有public、protected、private以及static修饰符的。
         */
        class InnerClass extends MyMethodInnerClass {

            private String name = "李四";

            public void printInfo() {
                System.out.println("MyMethodInnerClass.this.name = " + MyMethodInnerClass.this.name);
                System.out.println("MyMethodInnerClass$.name = " + name);
            }
        }

        InnerClass innerClass = new InnerClass();
        innerClass.printInfo();
    }

    public static void main(String[] args) {
        MyMethodInnerClass myMethodInnerClass = new MyMethodInnerClass();
        myMethodInnerClass.method();
    }

}

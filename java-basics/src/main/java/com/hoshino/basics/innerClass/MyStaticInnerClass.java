package com.hoshino.basics.innerClass;

/**
 * 静态内部类
 * @author Akino
 * @date 2023-08-07
 */
public class MyStaticInnerClass {

    private double radius = 3.14;

    private static Integer count = 0;

    private static final Integer LENGTH = 5;

    public MyStaticInnerClass(double radius) {
        this.radius = radius;
        count++;
    }

    public Draw getDrawInstance() {
        return new Draw();
    }

    public static class Draw {

        private static Integer count = 0;

        // 访问外部类的静态变量，包括 private 修饰的变量
        private void drawShape() {
            System.out.println("MyMemberInnerClass$Draw.count = " + count);
            System.out.println("MyMemberInnerClass.this.count = " + MyStaticInnerClass.count);
            System.out.println("MyMemberInnerClass.this.LENGTH = " + LENGTH);
        }
    }

    public static void main(String[] args) {
        // 1.使用 外部类名.内部类名 方式实例化内部类，类似访问静态变量
        MyStaticInnerClass.Draw draw = new MyStaticInnerClass.Draw();
        draw.drawShape();
        System.out.println("------------------------------------------------");

        // 2.外部类实现获取内部类对象方法
        Draw drawInstance = new MyStaticInnerClass(10).getDrawInstance();
        drawInstance.drawShape();
        System.out.println("------------------------------------------------");

        Draw drawInstance1 = new MyStaticInnerClass(10).getDrawInstance();
        drawInstance1.drawShape();
        System.out.println("------------------------------------------------");
    }
    
}

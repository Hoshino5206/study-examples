package com.hoshino.basics.innerClass;

/**
 * 成员内部类
 * @author Akino
 * @date 2023-08-07
 */
public class MyMemberInnerClass {

    private final double radius;

    private static Integer count = 0;

    private static final Integer LENGTH = 5;

    public MyMemberInnerClass(double radius) {
        this.radius = radius;
        count++;
    }

    public Draw getDrawInstance() {
        return new Draw();
    }

    public class Draw {

        private final double radius = 3.1415926;

        // 访问外部类的成员变量，包括 private 修饰的变量
        private void drawShape() {
            // 如果重名，则就近原则覆盖，如果想访问重名的外部类变量，则使用 外部类名.this.成员变量 方式访问
            System.out.println("MyMemberInnerClass$Draw.radius = " + radius);
            System.out.println("MyMemberInnerClass.this.radius = " + MyMemberInnerClass.this.radius);
            System.out.println("MyMemberInnerClass.this.count = " + count);
            System.out.println("MyMemberInnerClass.this.LENGTH = " + LENGTH);
        }
    }

    public static void main(String[] args) {
        // 1.实例化外部类对象，再实例化内部类对象
        MyMemberInnerClass outer = new MyMemberInnerClass(3.14);
        Draw inner = outer.new Draw();
        inner.drawShape();
        System.out.println("------------------------------------------------");

        // 2.第一种方式合并操作实例化内部类对象
        MyMemberInnerClass.Draw draw = new MyMemberInnerClass(3.14).new Draw();
        draw.drawShape();
        System.out.println("------------------------------------------------");

        // 3.外部类实现获取内部类对象方法
        Draw drawInstance = new MyMemberInnerClass(3.14).getDrawInstance();
        drawInstance.drawShape();
        System.out.println("------------------------------------------------");

    }

}

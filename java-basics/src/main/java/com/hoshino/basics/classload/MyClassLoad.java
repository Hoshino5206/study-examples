package com.hoshino.basics.classload;

/**
 * @author huangyuehao
 * @date 2022-11-23
 */
public class MyClassLoad extends MyParentClassLoad {

    static int c = 30;

    static {
        System.out.println("children class static module, c = " + c);
    }

    static int d = 40;

    static {
        System.out.println("children class static module, c = " + c + ", d = " + d);
    }

    public static void staticOut() {
        System.out.println("static method(out), c = " + c + ", d = " + d);
    }

    public void children() {
        System.out.println("not static method(out), c = " + c + ", d = " + d);
    }

    public static void main(String[] args) {
        System.out.println("start");
        MyParentClassLoad.staticOut();
        MyClassLoad.staticOut();
        MyParentClassLoad mpc = new MyParentClassLoad();
        mpc.out();
        MyClassLoad mcl = new MyClassLoad();
        mcl.children();
        System.out.println("end");
    }

}

package com.hoshino.basics.math;

/**
 * @author huangyuehao
 * @date 2023-02-20
 */
public class MyMath {

    public static void main(String[] args) {
        // 7 = 00111 = 1*2^2 + 1*2^1 + 1*2^0
        int a = 7;
        System.out.println("a = " + Integer.toBinaryString(a));
        // <<左移运算符
        int b = a << 1;
        System.out.println("b = " + b);
        // >>右移运算符
        int c = a >> 1;
        System.out.println("c = " + c);
        // 按位与&，13 = 01101，7&13 = 00101 = 5
        int d = a & 13;
        System.out.println("d = " + d);
        // 按位或|，9 = 01001，7&9 = 00001 = 1
        int e = a & 9;
        System.out.println("e = " + e);
        // 按位取反，8 = 01000，～8 = 10111 = -9
        int f = ~ 8;
        System.out.println("f = " + f);
        // 按位异或^，4 = 00100，a ^ 4 = 00011 = 3
        int g = a ^ 4;
        System.out.println("g = " + g);

    }

}

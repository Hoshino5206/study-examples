package com.hoshino.algorithm.斐波那契数列;

/**
 * @author Yy_hoshino
 * @date 2021-07-21 0:19
 */
public class Fibonacci {
    /*
    暴力解法
     */
    public static int fibonacci1(int n) {
        int ans[] = new int[40];
        ans[0] = 0;
        ans[1] = 1;
        for (int i = 2; i <= n; i++) {
            ans[i] = ans[i - 1] + ans[i - 2];
        }
        return ans[n];
    }

    /*
    递归法
     */
    public static int fibonacci2(int n) {
        if (n==0 || n==1) return n;
        return fibonacci2(n-1) + fibonacci2(n-2);
    }

    /*
    优化存储
     */
    public static int fibonacci3(int n) {
        int a = 0, b = 1;
        for (int i = 1; i <= n; i++) {
            a = a + b;
            b = a - b;
        }
        return a;
    }

    /*
    待定系数法
     */
    public static int fibonacci4(int n) {
        double a = Math.sqrt(5)/5;
        double b = Math.pow((1+ Math.sqrt(5))/2, n);
        double c = Math.pow((1- Math.sqrt(5))/2, n);
        return (int)(a * (b - c));
    }

    public static void main(String[] args) {
        int fib = fibonacci1(8);
        System.out.println(fib);
    }
}
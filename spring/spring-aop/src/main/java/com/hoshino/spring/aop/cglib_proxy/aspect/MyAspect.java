package com.hoshino.spring.aop.cglib_proxy.aspect;

/**
 * 切面类：可以存在多个通知 Advice (即增强的方法)
 * @author Yy_hoshino
 * @date 2021-04-10 1:51
 */
public class MyAspect {

    public void checkPermissions() {
        System.out.println("模拟检查权限...");
    }

    public void log() {
        System.out.println("模拟记录日志...");
    }

}

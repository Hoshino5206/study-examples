package com.hoshino.spring.aop.jdk_proxy.aspect;

/**
 * @author Yy_hoshino
 * @date 2021-04-10 1:40
 */
public class MyAspect {

    public void check_Permissions() {
        System.out.println("模拟检查权限...");
    }

    public void log() {
        System.out.println("模拟记录日志...");
    }

}

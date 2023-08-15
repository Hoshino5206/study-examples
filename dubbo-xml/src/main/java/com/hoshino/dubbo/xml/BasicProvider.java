package com.hoshino.dubbo.xml;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CountDownLatch;

/**
 * @author huangyuehao
 * @date 2023-02-02
 */
public class BasicProvider {

    public static void main (String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo-demo-provider.xml");
        context.start();

        // 挂起主线程，防止退出
        new CountDownLatch(1).await();
    }
}

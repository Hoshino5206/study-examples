package com.hoshino.dubbo.xml;

import com.hoshino.dubbo.xml.api.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author huangyuehao
 * @date 2023-02-02
 */
public class BasicConsumer {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo-demo-consumer.xml");
        context.start();

        DemoService demoService = (DemoService) context.getBean("demoService");
        String message = demoService.sayHello("dubbo");
        System.out.println("Receive result ======> " + message);

        // 挂起主线程，防止退出
        new CountDownLatch(1).await();
    }

}

package com.hoshino.dubbo.xml;

import com.hoshino.dubbo.xml.api.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author huangyuehao
 * @date 2023-02-02
 */
public class BasicConsumer {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("dubbo-demo-consumer.xml");
        context.start();

        DemoService demoService = (DemoService) context.getBean("demoService");
        String hello = demoService.sayHello("World");
        System.out.println(hello);
        System.in.read();
    }

}

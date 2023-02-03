package com.hoshino.dubbo.annotation;

import com.hoshino.dubbo.annotation.api.HelloService;
import com.hoshino.dubbo.annotation.config.ConsumerConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author huangyuehao
 * @date 2023-02-03
 */
public class AnnotationConsumerBootstrap {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ConsumerConfiguration.class);
        context.start();

        HelloService service = (HelloService) context.getBean("helloService");
        String message = service.sayHello("Dubbo");
        System.out.println("message = " + message);
    }

}

package com.hoshino.dubbo.api;

import com.hoshino.dubbo.api.api.DemoService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;

import java.io.IOException;

/**
 * @author huangyuehao
 * @date 2023-02-03
 */
public class BasicConsumerByDubboBootstrap {

    public static void main (String[] args) throws IOException {
        // 当前应用配置
        ApplicationConfig application = new ApplicationConfig();
        application.setName("first-demo-consumer");
        application.setQosEnable(true);
        application.setQosAcceptForeignIp(false);
        // 服务提供者已经把22222端口占用了，那么服务消费者就需要更改端口了
        application.setQosPort(22223);

        ReferenceConfig<DemoService> reference = new ReferenceConfig<>();
        reference.setInterface(DemoService.class);

        DubboBootstrap.getInstance()
                .application(application)
                .registry(new RegistryConfig("zookeeper://127.0.0.1:2181"))
                .reference(reference)
                .start();

        DemoService service = reference.get();
        String message = service.sayHello("Dubbo");
        System.out.println("Receive result ======> " + message);

        // 挂起等待(防止进程退出）
        System.in.read();
    }

}

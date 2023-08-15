package com.hoshino.dubbo.api;

import com.hoshino.dubbo.api.api.DemoService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

import java.io.IOException;

/**
 * @author huangyuehao
 * @date 2023-02-03
 */
public class BasicConsumer {

    public static void main(String[] args) throws IOException {
        // 当前应用配置
        ApplicationConfig application = new ApplicationConfig();
        application.setName("demo-consumer");
        application.setQosEnable(true);
        application.setQosAcceptForeignIp(false);
        // 服务提供者已经把22222端口占用了，那么服务消费者就需要更改端口了
        application.setQosPort(22223);

        // 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("zookeeper://127.0.0.1:2181");

        // 注意：ReferenceConfig为重对象，内部封装了与注册中心的连接，以及与服务提供方的连接
        // 引用远程服务
        // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
        ReferenceConfig<DemoService> reference = new ReferenceConfig<>();
        reference.setApplication(application);
        // 多个注册中心可以用setRegistries()
        reference.setRegistry(registry);
        reference.setInterface(DemoService.class);
        reference.setVersion("1.0.0");

        // 和本地bean一样使用demoService
        // 注意：此代理对象内部封装了所有通讯细节，对象较重，请缓存复用
        DemoService demoService = reference.get();
        String message = demoService.sayHello("dubbo");
        System.out.println("Receive result ======> " + message);

        // 挂起等待(防止进程退出）
        System.in.read();
    }

}

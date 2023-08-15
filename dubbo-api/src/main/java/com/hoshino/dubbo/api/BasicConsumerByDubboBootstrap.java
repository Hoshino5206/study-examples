package com.hoshino.dubbo.api;

import com.hoshino.dubbo.api.api.DemoService;
import org.apache.dubbo.config.*;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author huangyuehao
 * @date 2023-02-03
 */
public class BasicConsumerByDubboBootstrap {

    private static final Logger log = LoggerFactory.getLogger(BasicConsumerByDubboBootstrap.class);

    public static void main (String[] args) throws IOException {
        // 当前应用配置
        ApplicationConfig application = new ApplicationConfig();
        application.setName("demo-consumer");

        // 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("zookeeper://127.0.0.1:2181");
        registry.setTimeout(5000);

        // 配置中心
        ConfigCenterConfig configCenter = new ConfigCenterConfig();
        configCenter.setAddress("zookeeper://127.0.0.1:2181");
        configCenter.setTimeout(5000L);

        // 元数据中心
        MetadataReportConfig metadataReport = new MetadataReportConfig();
        metadataReport.setAddress("zookeeper://127.0.0.1:2181");
        metadataReport.setTimeout(5000);

        // 引用远程服务
        ReferenceConfig<DemoService> demoServiceReference = new ReferenceConfig<DemoService>();
        demoServiceReference.setInterface(DemoService.class);
        demoServiceReference.setVersion("1.0.0");

        DubboBootstrap.getInstance()
                .application(application)
                .registry(registry)
                .configCenter(configCenter)
                .metadataReport(metadataReport)
                .reference(demoServiceReference)
                .start();
        log.info("dubbo consumer start successfully ......");

        DemoService service = demoServiceReference.get();
        String message = service.sayHello("Dubbo");
        log.info("Receive result ======> {}", message);

        // 挂起等待(防止进程退出）
        System.in.read();
    }

}

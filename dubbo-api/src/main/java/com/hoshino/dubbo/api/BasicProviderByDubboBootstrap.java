package com.hoshino.dubbo.api;

import com.hoshino.dubbo.api.api.DemoService;
import com.hoshino.dubbo.api.impl.DemoServiceImpl;
import org.apache.dubbo.config.*;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author huangyuehao
 * @date 2023-02-03
 */
public class BasicProviderByDubboBootstrap {

    private static final Logger log = LoggerFactory.getLogger(BasicProviderByDubboBootstrap.class);

    public static void main(String[] args) {
        // 当前应用配置
        ApplicationConfig application = new ApplicationConfig();
        application.setName("demo-provider");

        // 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("zookeeper://127.0.0.1:2181");
        registry.setTimeout(5000);

        // 服务提供者协议配置
        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName("dubbo");
        protocol.setPort(12345);
        protocol.setThreads(200);

        // 配置中心
        ConfigCenterConfig configCenter = new ConfigCenterConfig();
        configCenter.setAddress("zookeeper://127.0.0.1:2181");
        configCenter.setTimeout(5000L);

        // 元数据中心
        MetadataReportConfig metadataReport = new MetadataReportConfig();
        metadataReport.setAddress("zookeeper://127.0.0.1:2181");
        metadataReport.setTimeout(5000);

        // Metrics
        MetricsConfig metrics = new MetricsConfig();
        metrics.setProtocol("dubbo");

        // 注意：ServiceConfig为重对象，内部封装了与注册中心的连接，以及开启服务端口
        // 服务提供者暴露服务配置
        ServiceConfig<DemoService> service = new ServiceConfig<>();
        service.setInterface(DemoService.class);
        service.setRef(new DemoServiceImpl());
        service.setVersion("1.0.0");

        DubboBootstrap.getInstance()
                .application(application)
                .registry(registry)
                .protocol(protocol)
                .configCenter(configCenter)
                .metadataReport(metadataReport)
                .metrics(metrics)
                .service(service)
                .start()
                .await();
        log.info("dubbo provider start successfully ......");
    }

}

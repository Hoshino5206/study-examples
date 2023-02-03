package com.hoshino.dubbo.api;

import com.hoshino.dubbo.api.api.DemoService;
import com.hoshino.dubbo.api.impl.DemoServiceImpl;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;

import java.io.IOException;

/**
 * @author huangyuehao
 * @date 2023-02-03
 */
public class BasicProviderByDubboBootstrap {

    public static void main(String[] args) throws IOException {

        ServiceConfig<DemoService> service = new ServiceConfig<>();
        service.setInterface(DemoService.class);
        service.setRef(new DemoServiceImpl());

        DubboBootstrap.getInstance()
                .application("first-dubbo-provider")
                .registry(new RegistryConfig("zookeeper://127.0.0.1:2181"))
                .service(service)
                .start();

        // 挂起等待(防止进程退出）
        System.in.read();
    }

}

package com.hoshino.dubbo.api;

import com.hoshino.dubbo.api.api.DemoService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.junit.Test;

/**
 * @author huangyuehao
 * @date 2023-02-03
 */
public class DemoServiceTest {

    @Test
    public void test() {
        ReferenceConfig<DemoService> reference = new ReferenceConfig<>();
        reference.setApplication(new ApplicationConfig("first-dubbo-consumer"));
        reference.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        reference.setInterface(DemoService.class);
        DemoService service = reference.get();
        String message = service.sayHello("Dubbo");
        System.out.println("Receive result ======> " + message);
    }

}

package com.hoshino.dubbo.springboot;

import java.util.concurrent.CompletableFuture;

/**
 * @author huangyuehao
 * @date 2023-02-03
 */
public interface DemoService {

    String sayHello(String name);

    default CompletableFuture<String> sayHelloAsync(String name) {
        return CompletableFuture.completedFuture(sayHello(name));
    }

}

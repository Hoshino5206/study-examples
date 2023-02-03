package com.hoshino.dubbo.annotation.api;

/**
 * @author huangyuehao
 * @date 2023-02-03
 */
public interface HelloService {

    String sayHello(String name);

    default String sayGoodbye(String name) {
        return "Goodbye, " + name;
    }

}

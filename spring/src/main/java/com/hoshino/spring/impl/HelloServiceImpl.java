package com.hoshino.spring.impl;

import com.hoshino.spring.api.HelloService;

/**
 * @author huangyuehao
 * @date 2023-02-06
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public void sayHello (String message) {
        System.out.println("hello world");
    }
}

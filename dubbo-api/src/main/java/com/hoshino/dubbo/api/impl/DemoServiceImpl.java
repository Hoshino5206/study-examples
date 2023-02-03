package com.hoshino.dubbo.api.impl;

import com.hoshino.dubbo.api.api.DemoService;

/**
 * @author huangyuehao
 * @date 2023-02-02
 */
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }

}

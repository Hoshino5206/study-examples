package com.hoshino.example.boot.listener;

import com.hoshino.example.boot.even.OrderEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * @author huangyuehao
 * @date 2022-09-05
 */
public class DemoEventPublisher {
    @Autowired
    private ApplicationContext applicationContext;

    public void pushlish(Object o){
        applicationContext.publishEvent(new OrderEvent(this,o));
    }
}

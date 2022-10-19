package com.hoshino.study.boot.even;

import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * @author huangyuehao
 * @date 2022-09-05
 */
public class OrderEvent extends ApplicationEvent {

    private Object object;

    public OrderEvent(Object source, Object obj) {
        super(source);
        this.object = obj;
    }

    public Object getObject () {
        return object;
    }
}

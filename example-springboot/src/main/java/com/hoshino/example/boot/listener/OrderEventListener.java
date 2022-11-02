package com.hoshino.example.boot.listener;

import com.hoshino.example.boot.even.OrderEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author huangyuehao
 * @date 2022-09-05
 */
@Component
public class OrderEventListener implements ApplicationListener<OrderEvent> {
    @Async
    @Override
    public void onApplicationEvent(OrderEvent event) {
        //真正做业务的地方
        try {
            System.out.println("开始做事"+ Thread.currentThread().getName());
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String s = event.getObject().toString();
        System.out.println("结束做事"+s);
    }
}

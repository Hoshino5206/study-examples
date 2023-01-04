package com.hoshino.springboot.rabbitmq.consumer;

import com.hoshino.springboot.rabbitmq.config.RabbitmqConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author huangyuehao
 * @date 2023-01-04
 */
@Component
@RabbitListener(queues = RabbitmqConfig.FANOUT_QUEUE_A)
public class FanoutReceiverA {

    @RabbitHandler
    public void process(Map testMessage) {
        System.out.println("FanoutReceiver消费者A收到消息  : " + testMessage.toString());
    }

}

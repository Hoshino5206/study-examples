package com.hoshino.springboot.rabbitmq.consumer;

import com.hoshino.springboot.rabbitmq.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author huangyuehao
 * @date 2023-01-04
 */
@Component
@RabbitListener(queues = RabbitMQConfig.FANOUT_QUEUE_B)
public class FanoutQueueConsumerB {

    @RabbitHandler
    public void onMessage(@Payload String message){
        System.out.println("消费者接收 FanoutQueue_B 队列, Message content : " + message);
    }

    @RabbitHandler
    public void process(@Payload Map<Object, Object> message) {
        System.out.println("消费者接收 FanoutQueue_B 队列, Message content : " + message.toString());
    }
    
}

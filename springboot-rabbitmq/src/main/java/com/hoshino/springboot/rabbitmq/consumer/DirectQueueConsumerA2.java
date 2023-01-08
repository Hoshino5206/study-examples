package com.hoshino.springboot.rabbitmq.consumer;

import com.hoshino.springboot.rabbitmq.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 实现了轮询的方式对消息进行消费，而且不存在重复消费.
 * @author huangyuehao
 * @date 2023-01-04
 */
@Component
@RabbitListener(queues = RabbitMQConfig.DIRECT_QUEUE_A)
public class DirectQueueConsumerA2 {

    @RabbitHandler
    public void onMessage(@Payload String message){
        System.out.println("消费者2接收 DirectQueue_A 队列, Message content : " + message);
    }

    @RabbitHandler
    public void process(@Payload Map<Object, Object> message) {
        System.out.println("消费者2接收 DirectQueue_A 队列, Message content : " + message.toString());
    }

}

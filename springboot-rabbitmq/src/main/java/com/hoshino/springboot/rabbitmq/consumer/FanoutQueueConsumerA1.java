package com.hoshino.springboot.rabbitmq.consumer;

import com.hoshino.springboot.rabbitmq.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 用@RabbitListener注解在类上，然后用@RabbitHandler注解在方法上，根据方法参数的不同自动识别并去消费.
 * @author huangyuehao
 * @date 2023-01-04
 */
@Component
@RabbitListener(queues = RabbitMQConfig.FANOUT_QUEUE_A)
public class FanoutQueueConsumerA1 {

    @RabbitHandler
    public void onMessage(@Payload String message){
        System.out.println("消费者1接收 FanoutQueue_A 队列, Message content : " + message);
    }

    @RabbitHandler
    public void process(@Payload Map<Object, Object> message) {
        System.out.println("消费者1接收 FanoutQueue_A 队列, Message content : " + message.toString());
    }

}

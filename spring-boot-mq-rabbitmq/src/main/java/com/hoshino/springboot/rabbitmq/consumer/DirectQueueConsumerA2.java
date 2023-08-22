package com.hoshino.springboot.rabbitmq.consumer;

import com.hoshino.springboot.rabbitmq.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 实现了轮询的方式对消息进行消费，而且不存在重复消费.
 * @author huangyuehao
 * @date 2023-01-04
 */
@Component
@Slf4j
public class DirectQueueConsumerA2 {

    @RabbitListener(queues = RabbitMQConfig.DIRECT_QUEUE_A)
    public void process(@Payload Map<Object, Object> message, @Headers Map<String, Object> heads) {
        log.info("消费者A2接收到 DirectQueueA 队列消息");
        log.info("message: {}", message);
        log.info("heads: {}", heads);
    }

}

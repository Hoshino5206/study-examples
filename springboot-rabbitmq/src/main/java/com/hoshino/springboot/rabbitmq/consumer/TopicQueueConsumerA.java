package com.hoshino.springboot.rabbitmq.consumer;

import com.hoshino.springboot.rabbitmq.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author huangyuehao
 * @date 2023-01-04
 */
@Component
@Slf4j
public class TopicQueueConsumerA {

    @RabbitListener(queues = RabbitMQConfig.TOPIC_QUEUE_A)
    public void process(@Payload Map<Object, Object> message, @Headers Map<String, Object> heads) {
        log.info("消费者A接收到 TopicQueueA 队列消息");
        log.info("message: {}", message);
        log.info("heads: {}", heads);
    }

}

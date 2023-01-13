package com.hoshino.springboot.rabbitmq.consumer;

import com.hoshino.springboot.rabbitmq.config.RabbitMQConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * 延迟队列消费者.
 * @author huangyuehao
 * @date 2023-01-13
 */
@Component
@Slf4j
public class DelayQueueConsumer {

    @RabbitListener(queues = RabbitMQConfig.DELAY_QUEUE)
    public void process(@Payload Map<Object, Object> message,
                        @Headers Map<String, Object> heads,
                        Message msg, Channel channel) throws IOException {
        log.info("消费者接收到 DelayQueue 队列消息");
        log.info("message: {}", message);
        log.info("heads: {}", heads);

//        long deliveryTag = msg.getMessageProperties().getDeliveryTag();
        Long deliveryTag = (Long) heads.get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(deliveryTag, true);
        log.info("手动确认消费 DelayQueue 队列 的消息");
    }

}

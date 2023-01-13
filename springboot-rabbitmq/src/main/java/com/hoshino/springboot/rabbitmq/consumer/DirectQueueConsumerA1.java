package com.hoshino.springboot.rabbitmq.consumer;

import com.hoshino.springboot.rabbitmq.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 1.@RabbitListener 可以标注在类上面，需配合 @RabbitHandler 注解一起使用.
 * 2.@RabbitListener 标注在类上面表示当有收到消息的时候，就交给 @RabbitHandler 的方法处理，具体使用哪个方法处理，根据 MessageConverter 转换后的参数类型.
 * 3.使用 @Payload 和 @Headers 注解可以消息中的 body 与 headers 信息.
 * @RabbitHandler
 * public void processMessage(String message) {
 *     System.out.println(message);
 * }
 * @RabbitHandler
 * public void processMessage(byte[] message) {
 *     System.out.println(new String(message));
 * }
 * @RabbitHandler
 * public void processMessage(Map<String, String> message) {
 *     System.out.println(message);
 * }
 * @author huangyuehao
 * @date 2023-01-04
 */
@Component
@Slf4j
public class DirectQueueConsumerA1 {

    @RabbitListener(queues = RabbitMQConfig.DIRECT_QUEUE_A)
    public void process(@Payload Map<Object, Object> message, @Headers Map<String, Object> heads) {
        log.info("消费者A1接收到 DirectQueueA 队列消息");
        log.info("message: {}", message);
        log.info("heads: {}", heads);
    }

}

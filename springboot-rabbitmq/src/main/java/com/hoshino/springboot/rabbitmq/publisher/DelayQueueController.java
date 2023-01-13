package com.hoshino.springboot.rabbitmq.publisher;

import com.hoshino.springboot.rabbitmq.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 利用 RabbitMQ 插件实现延迟队列.
 * 死信队列 + ttl方式实现延迟队列存在一个严重问题: RabbitMQ 只会检查第一个消息是否过期，如果过期则丢到死信队列。索引如果第一个消息的延时时长很长，而第二个消息的延时时长很短，则第二个消息并不会优先得到执行.
 * @author huangyuehao
 * @date 2023-01-13
 */
@RestController
public class DelayQueueController {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendDelayQueueMessage1")
    public String sendDelayQueueMessage1() {
        rabbitTemplate.convertAndSend(RabbitMQConfig.DELAY_EXCHANGE, RabbitMQConfig.DELAY_ROUTING_KEY, getMessage("delay queue ttl = 10s"),
        message -> {
            // 通过死信实现延迟队列, 当第二条消息的ttl大于第一条时，会阻塞队列。使用setExpiration设置ttl
            // 通过插件实现，不阻塞的延迟队列。使用setDelay设置ttl
//            message.getMessageProperties().setExpiration("10000");
            message.getMessageProperties().setDelay(10000);
            return message;
        });
        return "ok";
    }

    @GetMapping("/sendDelayQueueMessage2")
    public String sendDelayQueueMessage2() {
        rabbitTemplate.convertAndSend(RabbitMQConfig.DELAY_EXCHANGE, RabbitMQConfig.DELAY_ROUTING_KEY, getMessage("delay queue ttl = 20s"),
        message -> {
            // 设置消息过期时间20s
            message.getMessageProperties().setDelay(20000);
            return message;
        });
        return "ok";
    }

    private Map<String, String> getMessage(String message) {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = message + " message, hello! ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, String> map = new HashMap<String, String>(8);
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        return map;
    }

}

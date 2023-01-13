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
 * RabbitMQ 信息进入死信队列的三种情况:
 * 1.消息被否定确认，使用 channel.basicNack 或 channel.basicReject ，并且此时requeue 属性被设置为false。
 * 2.消息在队列的存活时间超过设置的TTL时间。
 * 3.消息队列的消息数量已经超过最大队列长度。
 * @author huangyuehao
 * @date 2023-01-11
 */
@RestController
public class DeadLetterQueueController {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendDeadQueueMessage1")
    public String sendDeadQueueMessage1() {
        rabbitTemplate.convertAndSend(RabbitMQConfig.NORMAL_EXCHANGE, RabbitMQConfig.LENGTH_ROUTING_KEY, getMessage(" normal 队列消息，最大长度为: 5 "));
        return "ok";
    }

    @GetMapping("/sendDeadQueueMessage2")
    public String sendDeadQueueMessage2() {
        rabbitTemplate.convertAndSend(RabbitMQConfig.NORMAL_EXCHANGE, RabbitMQConfig.TTL_ROUTING_KEY, getMessage(" normal 队列消息，消息过期时间为: 10秒 "));
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

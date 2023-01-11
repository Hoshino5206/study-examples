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
 * @author huangyuehao
 * @date 2023-01-11
 */
@RestController
public class DlxQueueController {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendDlxQueueMessage1")
    public String sendDlxQueueMessage1() {
        rabbitTemplate.convertAndSend(RabbitMQConfig.NORMAL_EXCHANGE, RabbitMQConfig.LENGTH_ROUTING_KEY, getMessage(" normal 队列消息，最大长度为: 5 "));
        return "ok";
    }

    @GetMapping("/sendDlxQueueMessage2")
    public String sendDlxQueueMessage2() {
        rabbitTemplate.convertAndSend(RabbitMQConfig.NORMAL_EXCHANGE, RabbitMQConfig.TTL_ROUTING_KEY, getMessage(" normal 队列消息，消息过期时间为: 60秒 "));
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

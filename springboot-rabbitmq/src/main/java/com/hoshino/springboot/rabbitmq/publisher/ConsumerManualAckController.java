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
 * RabbitMQ 保证消息可靠性, 手动确认消费.
 * @author huangyuehao
 * @date 2023-01-05
 */
@RestController
public class ConsumerManualAckController {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendManualAckMessage1")
    public String sendManualAckMessage1() {
        rabbitTemplate.convertAndSend(RabbitMQConfig.MANUAL_ACK_EXCHANGE, RabbitMQConfig.MANUAL_ACK_ROUTING_KEY1, getMessage("manual_ack_queue1"));
        return "ok";
    }

    @GetMapping("/sendManualAckMessage2")
    public String sendManualAckMessage2() {
        rabbitTemplate.convertAndSend(RabbitMQConfig.MANUAL_ACK_EXCHANGE, RabbitMQConfig.MANUAL_ACK_ROUTING_KEY2, getMessage("manual_ack_queue2"));
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

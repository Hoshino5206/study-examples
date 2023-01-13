package com.hoshino.springboot.rabbitmq.publisher;

import com.hoshino.springboot.rabbitmq.config.RabbitMQConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * RabbitMQ 保证消息可靠投递.
 * 从总体的情况分析，生产者推送消息的消息确认，推送消息存在四种情况：
 * 1.消息推送到server，但是在server里找不到交换机
 * 2.消息推送到server，找到交换机了，但是没找到队列
 * 3.消息推送到sever，交换机和队列都没找到
 * 4.消息推送成功
 * @author huangyuehao
 * @date 2023-01-05
 */
@RestController
public class PublisherAckController {

    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * ①消息推送到server，但是在server里找不到交换机.
     * 这种情况触发的是 ConfirmCallback 回调函数.
     */
    @GetMapping("/sendDirectMessageAck1")
    public String sendDirectMessageAck1() {
        rabbitTemplate.convertAndSend("non-existent-exchange", RabbitMQConfig.DIRECT_ROUTING_KEY_A, getMessage("non-existent-exchange"));
        return "ok";
    }

    /**
     * ②消息推送到server，找到交换机了，但是没找到队列.
     * 这种情况触发的是 ConfirmCallback 和 ReturnCallback 两个回调函数.
     */
    @GetMapping("/sendDirectMessageAck2")
    public String sendDirectMessageAck2() {
        rabbitTemplate.convertAndSend(RabbitMQConfig.DIRECT_EXCHANGE, "non-existent-routingKey", getMessage("non-existent-queue"));
        return "ok";
    }

    /**
     * ③消息推送到sever，交换机和队列都没找到.
     * 这种情况触发的是 ConfirmCallback 回调函数.
     */
    @GetMapping("/sendDirectMessageAck3")
    public String sendDirectMessageAck3() {
        rabbitTemplate.convertAndSend("non-existent-exchange", "non-existent-routingKey", getMessage("non-existent-exchange and queue"));
        return "ok";
    }

    /**
     * ④消息推送成功.
     * 这种情况触发的是 ConfirmCallback 回调函数.
     */
    @GetMapping("/sendDirectMessageAck4")
    public String sendDirectMessageAck4() {
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(UUID.randomUUID().toString());
        Message message = new Message("hello, first message".getBytes(StandardCharsets.UTF_8));
        correlationData.setReturnedMessage(message);
        rabbitTemplate.convertAndSend(RabbitMQConfig.DIRECT_EXCHANGE, RabbitMQConfig.DIRECT_ROUTING_KEY_A, getMessage("direct queue A"), correlationData);
        return "ok";
    }

    private Map<String, String> getMessage(String message) {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = message + " !";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, String> map = new HashMap<String, String>(8);
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        return map;
    }

}

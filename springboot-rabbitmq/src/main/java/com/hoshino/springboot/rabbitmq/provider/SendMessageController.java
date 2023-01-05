package com.hoshino.springboot.rabbitmq.provider;

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
 * @author huangyuehao
 * @date 2023-01-04
 */
@RestController
public class SendMessageController {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendDirectMessage1")
    public String sendDirectMessage1() {
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(UUID.randomUUID().toString());
        Message message = new Message("hello, first message".getBytes(StandardCharsets.UTF_8));
        correlationData.setReturnedMessage(message);
        rabbitTemplate.convertAndSend(RabbitMQConfig.DIRECT_EXCHANGE, RabbitMQConfig.DIRECT_ROUTING_KEY_A, getMessage("direct queue A"), correlationData);
        return "ok";
    }

    @GetMapping("/sendDirectMessage2")
    public String sendDirectMessage2() {
        rabbitTemplate.convertAndSend(RabbitMQConfig.DIRECT_EXCHANGE, RabbitMQConfig.DIRECT_ROUTING_KEY_B, getMessage("direct queue B"));
        return "ok";
    }

    @GetMapping("/sendTopicMessage1")
    public String sendTopicMessage1() {
        rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE, "topic.A.message", getMessage("topic queue A"));
        return "ok";
    }

    @GetMapping("/sendTopicMessage2")
    public String sendTopicMessage2() {
        rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE, "topic.B.message", getMessage("topic queue B"));
        return "ok";
    }

    @GetMapping("/sendTopicMessage3")
    public String sendTopicMessage3() {
        rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE, "topic.C.queue.message", getMessage("topic queue C"));
        return "ok";
    }

    @GetMapping("/sendFanoutMessage")
    public String sendFanoutMessage() {
        rabbitTemplate.convertAndSend(RabbitMQConfig.FANOUT_EXCHANGE, "", getMessage("fanout queue"));
        return "ok";
    }

    public Map<String, String> getMessage(String message) {
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

package com.hoshino.springboot.rocketmq.consumer.broadcasting;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 广播消费模式 BROADCASTING：一条消息被多个consumer消费，即使这些consumer属于同一个ConsumerGroup，消息也会被ConsumerGroup中的每个Consumer都消费一次，广播消费中ConsumerGroup概念可以认为在消息划分方面无意义。
 * @author Akino
 * @date 2023-08-24
 */
@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "BroadcastingConsumerGroup", topic = "broadcasting", messageModel = MessageModel.BROADCASTING)
public class BroadcastingConsumer implements RocketMQListener<String> {

    @Value("${server.port}")
    private String serverPort;

    @Override
    public void onMessage(String msg) {
        log.info("ServerPort:{}, received message: {}", serverPort, msg);
    }
}

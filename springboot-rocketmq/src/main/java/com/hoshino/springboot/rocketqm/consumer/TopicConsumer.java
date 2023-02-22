package com.hoshino.springboot.rocketqm.consumer;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author huangyuehao
 * @date 2023-02-22
 */
@Component
@RocketMQMessageListener(consumerGroup = "topicConsumer", topic = "topic")
public class TopicConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String msg) {
        System.out.println("Received message : " + msg);
    }

}

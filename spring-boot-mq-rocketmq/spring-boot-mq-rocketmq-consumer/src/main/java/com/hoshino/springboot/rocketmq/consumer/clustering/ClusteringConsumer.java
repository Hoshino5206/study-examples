package com.hoshino.springboot.rocketmq.consumer.clustering;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 集群消费模式 CLUSTERING: 一个ConsumerGroup中的Consumer实例平均分摊消费消息。
 * 例如某个Topic有9条消息，其中一个ConsumerGroup有3个实例（可能是3个进程，或者3台机器），那么每个实例只消费其中部分，消费完的消息不能被其他实例消费。
 * @author huangyuehao
 * @date 2023-02-22
 */
@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "ClusteringConsumerGroup", topic = "clustering", messageModel = MessageModel.CLUSTERING)
public class ClusteringConsumer implements RocketMQListener<String> {

    @Value("${server.port}")
    private String serverPort;

    @Override
    public void onMessage(String msg) {
        log.info("ServerPort: {}, received message: {}", serverPort, msg);
    }
}

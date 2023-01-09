package com.hoshino.springboot.rabbitmq.config;

import com.hoshino.springboot.rabbitmq.consumer.ManualAckConsumeListener;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * 消息确认机制: 消息接受确认.
 * @author huangyuehao
 * @date 2023-01-09
 */
@Configuration
public class PullConfirmConfig {

    @Resource
    private CachingConnectionFactory connectionFactory;

    @Resource
    private ManualAckConsumeListener manualAckConsumeListener;

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setConcurrentConsumers(1);
        container.setMaxConcurrentConsumers(1);
        // RabbitMQ 默认是自动确认，这里改为手动确认消费
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        // 设置一个队列
//        container.setQueueNames(RabbitMQConfig.DIRECT_QUEUE_A);
        // 如果同时设置多个如下：前提是队列都是必须已经创建存在的
        container.setQueueNames(RabbitMQConfig.MANUAL_ACK_QUEUE1, RabbitMQConfig.MANUAL_ACK_QUEUE2);
        container.setMessageListener(manualAckConsumeListener);
        return container;
    }

}

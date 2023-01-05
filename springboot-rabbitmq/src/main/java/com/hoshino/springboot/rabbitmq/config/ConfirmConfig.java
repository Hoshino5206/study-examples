package com.hoshino.springboot.rabbitmq.config;

import com.hoshino.springboot.rabbitmq.consumer.ConsumerManualAckListener;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author huangyuehao
 * @date 2023-01-05
 */
@Configuration
public class ConfirmConfig {

    @Resource
    private CachingConnectionFactory connectionFactory;

    @Resource
    private ConsumerManualAckListener consumerManualAckListener;

    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        // 设置开启Mandatory,才能触发回调函数,无论消息推送结果怎么样都强制调用回调函数
        rabbitTemplate.setMandatory(true);

        // confirm 监听，当消息成功发到交换机 ack = true，没有发送到交换机 ack = false
        // correlationData 可在发送时指定消息唯一 id
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if(!ack){
                //记录日志、发送邮件通知、落库定时任务扫描重发
            }
            System.out.println("ConfirmCallback:     " + "相关数据：" + correlationData);
            System.out.println("ConfirmCallback:     " + "确认情况：" + ack);
            System.out.println("ConfirmCallback:     " + "原因：" + cause);
        });

        // 当消息成功发送到交换机没有路由到队列触发此监听
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            // 记录日志、发送邮件通知、落库定时任务扫描重发
            System.out.println("ReturnCallback:     " + "消息：" + message);
            System.out.println("ReturnCallback:     " + "回应码：" + replyCode);
            System.out.println("ReturnCallback:     " + "回应信息：" + replyText);
            System.out.println("ReturnCallback:     " + "交换机：" + exchange);
            System.out.println("ReturnCallback:     " + "路由键：" + routingKey);
        });

        return rabbitTemplate;
    }

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
        container.setMessageListener(consumerManualAckListener);
        return container;
    }


}

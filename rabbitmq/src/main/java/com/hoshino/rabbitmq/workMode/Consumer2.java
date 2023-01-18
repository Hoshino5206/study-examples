package com.hoshino.rabbitmq.workMode;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;
import com.hoshino.rabbitmq.utils.ConnectionUtil;

/**
 * 轮询分发
 * @author Yy_hoshino
 * @date 2022-06-02 17:38
 */
public class Consumer2 {

    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 接受消息时回调
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println("接收到的消息: " + new String(message.getBody()));
        };
        // 取消消息时回调
        CancelCallback cancelCallback = consumerTag -> {
            System.out.println(consumerTag + "消息消费被中断");
        };
        System.out.println("worker02 等待接收消息...");
        /**
         * 消费者消费信息
         * 1. 消费哪个队列
         * 2. 消费成功之后是否要自动应答
         * 3. 消费者成功消费的回调
         * 4. 消费者取消消费的回调
         */
        /**
         * 自动应答
         * 消费者从队列中获取消息，服务端如何知道消息已经被消费呢？
         * 此时需要改为手动应答
         */
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, cancelCallback);
    }
}
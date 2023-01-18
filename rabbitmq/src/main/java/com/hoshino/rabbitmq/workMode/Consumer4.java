package com.hoshino.rabbitmq.workMode;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;
import com.hoshino.rabbitmq.utils.ConnectionUtil;

import java.nio.charset.StandardCharsets;

/**
 * Work模式的“能者多劳”: 公平分发
 * @author Yy_hoshino
 * @date 2022-06-02 19:01
 */
public class Consumer4 {

    public static final String QUEUE_NAME = "ack_queue";

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 接受消息时回调
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("接收到的消息: " + new String(message.getBody(), StandardCharsets.UTF_8));
            /**
             * 1.消息的标记Tag
             * 2.是否批量应答
             */
            channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
        };
        // 取消消息时回调
        CancelCallback cancelCallback = consumerTag -> {
            System.out.println(consumerTag + "消息消费被中断");
        };
        System.out.println("worker04 等待接收消息...");
        /**
         * 消费者消费信息
         * 1. 消费哪个队列
         * 2. 消费成功之后是否要自动应答
         * 3. 消费者成功消费的回调
         * 4. 消费者取消消费的回调
         */
        /**
         * 手动应答
         * 设置不公平分发参数
         */
        channel.basicQos(1);
        channel.basicConsume(QUEUE_NAME, false, deliverCallback, cancelCallback);
    }
}
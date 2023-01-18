package com.hoshino.rabbitmq.simpleMode;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;
import com.hoshino.rabbitmq.utils.ConnectionUtil;

/**
 * @author Yy_hoshino
 * @date 2022-06-02 18:25
 */
public class Consumer {

    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 声明接受消息
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println("接收到的消息: " + new String(message.getBody()));
        };
        // 取消消息时回调
        CancelCallback cancelCallback = consumerTag -> {
            System.out.println(consumerTag + "消息消费被中断");
        };
        /**
         * 消费者消费信息
         * 1. 消费哪个队列
         * 2. 消费成功之后是否要自动应答
         * 3. 消费者成功消费的回调
         * 4. 消费者取消消费的回调
         */
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, cancelCallback);
    }
}

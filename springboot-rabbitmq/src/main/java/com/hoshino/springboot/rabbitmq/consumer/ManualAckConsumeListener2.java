package com.hoshino.springboot.rabbitmq.consumer;

import com.hoshino.springboot.rabbitmq.config.RabbitMQConfig;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 手动确认消费方式二：使用 @RabbitListener 直接监听指定队列.
 * @author huangyuehao
 * @date 2023-01-05
 */
@Component
public class ManualAckConsumeListener2 {

    @RabbitListener(queues = RabbitMQConfig.MANUAL_ACK_QUEUE1)
    public void onMessage(Message message, Channel channel) throws IOException {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        System.out.println("消费者2接收 manual_ack_queue1 队列, Message content : " + message);
        channel.basicAck(deliveryTag, true);
        System.out.println("手动确认消费 manual_ack_queue1 队列 的消息");
    }

    @RabbitListener(queues = RabbitMQConfig.MANUAL_ACK_QUEUE2)
    public void process(Message message, Channel channel) throws IOException {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        System.out.println("消费者2接收 manual_ack_queue2 队列, Message content : " + message.toString());
        channel.basicAck(deliveryTag, true);
        System.out.println("手动确认消费 manual_ack_queue2 队列 的消息");
    }

}

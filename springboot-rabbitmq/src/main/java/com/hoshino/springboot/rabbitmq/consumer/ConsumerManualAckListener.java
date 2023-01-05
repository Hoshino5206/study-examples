package com.hoshino.springboot.rabbitmq.consumer;

import com.hoshino.springboot.rabbitmq.config.RabbitMQConfig;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Map;

/**
 * 手动确认模式需要实现 ChannelAwareMessageListener.
 * @author huangyuehao
 * @date 2023-01-05
 */
@Component
public class ConsumerManualAckListener implements ChannelAwareMessageListener {

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            byte[] body = message.getBody();
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(body));
            Map<String, String> msgMap = (Map<String, String>) ois.readObject();
            String messageId = msgMap.get("messageId");
            String messageData = msgMap.get("messageData");
            String createTime = msgMap.get("createTime");
            ois.close();

            if (RabbitMQConfig.MANUAL_ACK_QUEUE1.equals(message.getMessageProperties().getConsumerQueue())) {
                System.out.println("消费的消息来自的队列名为: " + message.getMessageProperties().getConsumerQueue());
                System.out.println("消息成功消费到 messageId: " + messageId + ", messageData: " + messageData + ", createTime: " + createTime);
                System.out.println("执行 manual_ack_queue1 中的消息的业务处理流程......");

            }

            if (RabbitMQConfig.MANUAL_ACK_QUEUE2.equals(message.getMessageProperties().getConsumerQueue())) {
                System.out.println("消费的消息来自的队列名为: " + message.getMessageProperties().getConsumerQueue());
                System.out.println("消息成功消费到 messageId: " + messageId + ", messageData: " + messageData + ", createTime: " + createTime);
                System.out.println("执行 manual_ack_queue2 中的消息的业务处理流程......");

            }

            // 第二个参数，手动确认可以被批处理，当该参数为 true 时，则可以一次性确认 delivery_tag 小于等于传入值的所有消息
            channel.basicAck(deliveryTag, true);

            // 第二个参数，true会重新放回队列，所以需要自己根据业务逻辑判断什么时候使用拒绝
			// channel.basicReject(deliveryTag, true);

        } catch (Exception e) {
            channel.basicReject(deliveryTag, false);
            e.printStackTrace();
        }
    }

}
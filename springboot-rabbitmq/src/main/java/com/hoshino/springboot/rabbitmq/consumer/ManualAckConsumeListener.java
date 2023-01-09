package com.hoshino.springboot.rabbitmq.consumer;

import com.hoshino.springboot.rabbitmq.config.RabbitMQConfig;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * 手动确认消费方式一：实现 ChannelAwareMessageListener.
 * @author huangyuehao
 * @date 2023-01-05
 */
@Component
public class ManualAckConsumeListener implements ChannelAwareMessageListener {

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
//            byte[] body = message.getBody();
//            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(body));
//            Map<String, String> msgMap = (Map<String, String>) ois.readObject();
//            String messageId = msgMap.get("messageId");
//            String messageData = msgMap.get("messageData");
//            String createTime = msgMap.get("createTime");
//            ois.close();

            String messageId = message.getMessageProperties().getMessageId();
            String createTime = message.getMessageProperties().getTimestamp().toString();
            String messageData = message.getMessageProperties().getCorrelationId();

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

            /**
             * 限流策略
             * 预取设置其含义是允许该通道未确认交付的最大数量。一旦达到该值，rabbitmq将不再往该消费者传递更多的消息。
             * 其好处是能够避免内存消耗过大，合理的设置预取值能够增加其吞吐量。官方推荐100到300之间可提供最佳的吞吐量。
             * prefetchSize: 预取大小服务器将传递的最大内容量（以八位字节为单位），如果不受限制，则为0
             * prefetchCount: 服务器一次请求将传递的最大邮件数，如果没有限制，则为0
             * global: 是否将设置应用于整个通道，而不是每个消费者
             */
            channel.basicQos(10, 10, true);

            // deliveryTag 和 consumerTag区别：
            // 1、同一个会话， consumerTag 是固定的 可以做此会话的名字， deliveryTag 每次接收消息+1，可以做此消息处理通道的名字。
            // 2、deliveryTag 可以用来回传告诉 rabbitmq 这个消息处理成功 清除此消息（basicAck方法）。

            // basicAck方法是肯定的交付，一般在该消息处理完后执行，该消息才会在队列里面被删除，不然会处于UnAcked的状态存在队列中
            // deliveryTag: 唯一标识id，
            // multiple: 是否批量进行确认。当该参数为 true 时，则可以一次性确认 delivery_tag 小于等于传入值的所有消息
            channel.basicAck(deliveryTag, true);

             // basicNack也是否定的交付，其功能和basicReject是一样的。区别是basicNack比basicReject的功能更强一些。他能够一次丢弃多个或重排序多个消息
             channel.basicNack(deliveryTag, true, true);


        } catch (Exception e) {
            // basicReject是否定的交付，一般在消费消息时出现异常等的时候执行。可以将该消息丢弃或重排序去重新处理消息
            channel.basicReject(deliveryTag, true);
        }
    }

}

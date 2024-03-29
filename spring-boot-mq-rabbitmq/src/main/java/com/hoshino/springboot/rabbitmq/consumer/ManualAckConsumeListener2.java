package com.hoshino.springboot.rabbitmq.consumer;

import com.hoshino.springboot.rabbitmq.config.RabbitMQConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Map;

/**
 * 手动确认消费方式二: 实现 ChannelAwareMessageListener.
 * @author huangyuehao
 * @date 2023-01-05
 */
@Component
@Slf4j
public class ManualAckConsumeListener2 implements ChannelAwareMessageListener {

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
                log.info("消费者接收到 {} 队列消息", message.getMessageProperties().getConsumerQueue());
                log.info("消息成功消费到 messageId:{}, messageData:{}, createTime:{}", messageId, messageData, createTime);
            }

            if (RabbitMQConfig.MANUAL_ACK_QUEUE2.equals(message.getMessageProperties().getConsumerQueue())) {
                log.info("消费者接收到 {} 队列消息", message.getMessageProperties().getConsumerQueue());
                log.info("消息成功消费到 messageId:{}, messageData:{}, createTime:{}", messageId, messageData, createTime);
            }

            /**
             * 限流策略
             * 预取设置其含义是允许该通道未确认交付的最大数量。一旦达到该值，rabbitmq将不再往该消费者传递更多的消息。
             * 其好处是能够避免内存消耗过大，合理的设置预取值能够增加其吞吐量。官方推荐100到300之间可提供最佳的吞吐量。
             * prefetchSize: 服务器传送最大内容量（以八位字节计算），如果没有限制，则为0
             * prefetchCount: 服务器每次传递的最大消息数，如果没有限制，则为0
             * global: 如果为true,则当前设置将会应用于整个Channel(频道)
             */
            channel.basicQos(10, 10, true);
//            channel.basicQos(1);

            // deliveryTag 和 consumerTag区别：
            // 1、同一个会话， consumerTag 是固定的 可以做此会话的名字， deliveryTag 每次接收消息+1，可以做此消息处理通道的名字。
            // 2、deliveryTag 可以用来回传告诉 rabbitmq 这个消息处理成功 清除此消息（basicAck方法）。

            // basicAck方法是肯定的交付，一般在该消息处理完后执行，该消息才会在队列里面被删除，不然会处于UnAcked的状态存在队列中
            // deliveryTag: 唯一标识id，
            // multiple: 是否批量进行确认。当该参数为 true 时，则可以一次性确认 delivery_tag 小于等于传入值的所有消息
            channel.basicAck(deliveryTag, true);

            // basicNack也是否定的交付，其功能和basicReject是一样的。区别是basicNack比basicReject的功能更强一些。他能够一次丢弃多个或重排序多个消息
            channel.basicNack(deliveryTag, true, true);

            // 取消消费者对队列的订阅关系
            channel.basicCancel(message.getMessageProperties().getConsumerTag());

        } catch (Exception e) {
            // basicReject是否定的交付，一般在消费消息时出现异常等的时候执行。可以将该消息丢弃或重排序去重新处理消息
            channel.basicReject(deliveryTag, true);
        }
    }

}

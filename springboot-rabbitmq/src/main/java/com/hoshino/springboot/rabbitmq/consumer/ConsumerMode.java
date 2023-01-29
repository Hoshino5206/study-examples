package com.hoshino.springboot.rabbitmq.consumer;

import com.hoshino.springboot.rabbitmq.config.RabbitMQConfig;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.GetResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * RabbitMQ消费消息的两种模式: 推和拉.
 * 推模式: 消息中间件主动将消息推送给消费者，消费者调用channel.basicConsume方法订阅队列后，由RabbitMQ主动将消息推送给订阅队列的消费者。
 * 拉模式: 消费者主动从消息中间件拉取消息，消费者调用channel.basicGet方法，主动从指定队列中拉取消息。
 *
 * 1.推模式将消息提前推送给消费者，消费者必须设置一个缓冲区缓存这些消息。优点是消费者总是有一堆在内存中待处理的消息，所以当真正去消费消息时效率很高。缺点就是缓冲区可能会溢出。
 * 2.拉模式在消费者需要时才去消息中间件拉取消息，这段网络开销会明显增加消息延迟，降低系统吞吐量。
 * 3.不能在循环中使用拉模式来模拟推模式，因为拉模式每次都需要去消息中间件中拉取消息来消费，所以会严重影响RabbitMQ性能。
 * 4.要想实现高吞吐量，消费者需要使用推模式。
 * @author huangyuehao
 * @date 2023-01-16
 */
@Component
@Slf4j
public class ConsumerMode {

    @Resource
    private RabbitTemplate rabbitTemplate;

    // 消费者主动从MQ拉取消息,自动确认消费
    public String autoPullFromMQ() {
        Object obj = rabbitTemplate.receiveAndConvert(RabbitMQConfig.DIRECT_QUEUE_A);
        System.out.println("obj = " + new String(((byte[]) obj), StandardCharsets.UTF_8));
        return "pull from springboot rabbitmq";
    }

    // 消费者主动从MQ拉取消息,手动确认消费
    public void manualPullFromMQ() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = rabbitTemplate.getConnectionFactory();
        Connection connection = connectionFactory.createConnection();
        Channel channel = connection.createChannel(false);
        GetResponse getResponse = channel.basicGet(RabbitMQConfig.DIRECT_QUEUE_A, true);
        byte[] body = new byte[0];
        long deliveryTag = 0L;
        try {
            body = getResponse.getBody();
            deliveryTag = getResponse.getEnvelope().getDeliveryTag();
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            channel.basicNack(deliveryTag, false, true);
        } finally {
            channel.close();
        }
        log.info("body:{}", body);
        log.info("deliveryTag:{}", deliveryTag);
    }

    // MQ主动向消费者发送消息, 自动确认消费
    public void push() throws IOException {
        ConnectionFactory connectionFactory = rabbitTemplate.getConnectionFactory();
        Connection connection = connectionFactory.createConnection();
        Channel channel = connection.createChannel(false);
        // 声明接受消息
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println("接收到的消息: " + new String(message.getBody()));
        };
        // 取消消息时回调
        CancelCallback cancelCallback = consumerTag -> {
            System.out.println(consumerTag + "消息消费被中断");
        };
        channel.basicConsume(RabbitMQConfig.DIRECT_QUEUE_A, true, deliverCallback, cancelCallback);
    }

}

package com.hoshino.springboot.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * RabbitMQ 队列、交换机配置.
 * @author huangyuehao
 * @date 2023-01-04
 */
@Configuration
public class RabbitMQConfig {

    // ================================直连模式=================================== //
    public static final String DIRECT_QUEUE_A = "DirectQueueA";
    public static final String DIRECT_QUEUE_B = "DirectQueueB";
    public static final String DIRECT_EXCHANGE = "DirectExchange";
    public static final String DIRECT_ROUTING_KEY_A = "DirectRoutingKeyA";
    public static final String DIRECT_ROUTING_KEY_B = "DirectRoutingKeyB";
    /**
     * 与直连交换机绑定的队列A.
     */
    @Bean
    public Queue directQueueA() {
        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
        return new Queue(DIRECT_QUEUE_A, true, false, false, null);
    }

    /**
     * 与直连交换机绑定的队列B.
     */
    @Bean
    public Queue directQueueB() {
        return new Queue(DIRECT_QUEUE_B, true, false, false, null);
    }

    /**
     * 直连交换机.
     */
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(DIRECT_EXCHANGE, true, false, null);
    }

    /**
     * 直连交换机和队列A绑定.
     */
    @Bean
    public Binding bindingDirectA() {
        return BindingBuilder.bind(directQueueA()).to(directExchange()).with(DIRECT_ROUTING_KEY_A);
    }

    /**
     * 直连交换机和队列B绑定.
     */
    @Bean
    public Binding bindingDirectB() {
        return BindingBuilder.bind(directQueueB()).to(directExchange()).with(DIRECT_ROUTING_KEY_B);
    }


    // ================================主题匹配模式=================================== //
    public static final String TOPIC_QUEUE_A = "TopicQueueA";
    public static final String TOPIC_QUEUE_B = "TopicQueueB";
    public static final String TOPIC_QUEUE_C = "TopicQueueC";
    public static final String TOPIC_EXCHANGE = "TopicExchange";
    public static final String TOPIC_ROUTING_KEY_A = "topic.A.*";
    public static final String TOPIC_ROUTING_KEY_B = "topic.B.*";
    public static final String TOPIC_ROUTING_KEY_C = "topic.C.#";
    /**
     * 与主题交换机绑定的队列1.
     */
    @Bean
    public Queue topicQueueA() {
        return new Queue(TOPIC_QUEUE_A, true, false, false, null);
    }

    /**
     * 与主题交换机绑定的队列2.
     */
    @Bean
    public Queue topicQueueB() {
        return new Queue(TOPIC_QUEUE_B, true, false, false, null);
    }

    /**
     * 与主题交换机绑定的队列3.
     */
    @Bean
    public Queue topicQueueC() {
        return new Queue(TOPIC_QUEUE_C, true, false, false, null);
    }

    /**
     * 主题交换机.
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE, true, false, null);
    }


    /**
     * 主题交换机和队列1绑定.
     */
    @Bean
    public Binding bindingTopicA() {
        return BindingBuilder.bind(topicQueueA()).to(topicExchange()).with(TOPIC_ROUTING_KEY_A);
    }

    /**
     * 主题交换机和队列2绑定.
     */
    @Bean
    public Binding bindingTopicB() {
        return BindingBuilder.bind(topicQueueB()).to(topicExchange()).with(TOPIC_ROUTING_KEY_B);
    }

    /**
     * 主题交换机和队列3绑定.
     */
    @Bean
    public Binding bindingTopicC() {
        return BindingBuilder.bind(topicQueueC()).to(topicExchange()).with(TOPIC_ROUTING_KEY_C);
    }


    // ================================广播模式=================================== //
    public static final String FANOUT_QUEUE_A = "FanoutQueueA";
    public static final String FANOUT_QUEUE_B = "FanoutQueueB";
    public static final String FANOUT_QUEUE_C = "FanoutQueueC";
    public static final String FANOUT_EXCHANGE = "FanoutExchange";
    /**
     * 与扇形交换机绑定的队列A.
     */
    @Bean
    public Queue fanoutQueueA() {
        return new Queue(FANOUT_QUEUE_A, true, false, false, null);
    }

    /**
     * 与扇形交换机绑定的队列B.
     */
    @Bean
    public Queue fanoutQueueB() {
        return new Queue(FANOUT_QUEUE_B, true, false, false, null);
    }

    /**
     * 与扇形交换机绑定的队列C.
     */
    @Bean
    public Queue fanoutQueueC() {
        return new Queue(FANOUT_QUEUE_C, true, false, false, null);
    }

    /**
     * 扇形交换机.
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE, true, false, null);
    }

    /**
     * 扇形交换机和队列A绑定.
     */
    @Bean
    public Binding bindingFanoutA() {
        return BindingBuilder.bind(fanoutQueueA()).to(fanoutExchange());
    }

    /**
     * 扇形交换机和队列B绑定.
     */
    @Bean
    public Binding bindingFanoutB() {
        return BindingBuilder.bind(fanoutQueueB()).to(fanoutExchange());
    }

    /**
     * 扇形交换机和队列C绑定.
     */
    @Bean
    public Binding bindingFanoutC() {
        return BindingBuilder.bind(fanoutQueueC()).to(fanoutExchange());
    }


    // ================================手动确认消费=================================== //
    public static final String MANUAL_ACK_QUEUE1 = "ManualAckQueue1";
    public static final String MANUAL_ACK_QUEUE2 = "ManualAckQueue2";
    public static final String MANUAL_ACK_EXCHANGE = "ManualAckExchange";
    public static final String MANUAL_ACK_ROUTING_KEY1 = "ManualAckRoutingKey1";
    public static final String MANUAL_ACK_ROUTING_KEY2 = "ManualAckRoutingKey2";
    @Bean
    public Queue manualAckQueue1() {
        return new Queue(MANUAL_ACK_QUEUE1, true, false, false, null) ;
    }

    @Bean
    public Queue manualAckQueue2() {
        return new Queue(MANUAL_ACK_QUEUE2, true, false, false, null) ;
    }

    @Bean
    public DirectExchange manualAckExchange() {
        return new DirectExchange(MANUAL_ACK_EXCHANGE, true, false, null);
    }

    @Bean
    public Binding manualAckBinding1() {
        return BindingBuilder.bind(manualAckQueue1()).to(manualAckExchange()).with(MANUAL_ACK_ROUTING_KEY1);
    }

    @Bean
    public Binding manualAckBinding2() {
        return BindingBuilder.bind(manualAckQueue2()).to(manualAckExchange()).with(MANUAL_ACK_ROUTING_KEY2);
    }


    // ================================死信队列=================================== //
    public static final String LENGTH_QUEUE = "LengthQueue";
    public static final String TTL_QUEUE = "TTLQueue";
    public static final String NORMAL_EXCHANGE = "NormalExchange";
    public static final String LENGTH_ROUTING_KEY = "LengthRoutingKey";
    public static final String TTL_ROUTING_KEY  = "TTLRoutingKey";

    public static final String DEAD_LETTER_QUEUE = "DeadLetterQueue";
    public static final String DEAD_LETTER_EXCHANGE = "DeadLetterExchange";
    public static final String DEAD_LETTER_ROUTING_KEY = "DeadLetterRoutingKey";
    @Bean
    public Queue lengthQueue() {
        Map<String, Object> args = new HashMap<>(8);
        // 队列设置最大长度
        args.put("x-max-length", 5);
        // 绑定该队列到死信交换机，每个队列都可以指定属于自己的死信队列
        args.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE);
        args.put("x-dead-letter-routing-key", DEAD_LETTER_ROUTING_KEY);
        return new Queue(LENGTH_QUEUE, true, false, false, args);
    }

    @Bean
    public Queue ttlQueue() {
        Map<String, Object> args = new HashMap<>(8);
        // 队列设置消息过期时间10秒
        args.put("x-message-ttl", 10 * 1000);
        // 绑定该队列到死信交换机，每个队列都可以指定属于自己的死信队列
        args.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE);
        args.put("x-dead-letter-routing-key", DEAD_LETTER_ROUTING_KEY);
        return new Queue(TTL_QUEUE, true, false, false, args);
    }

    @Bean
    public DirectExchange normalExchange() {
        return new DirectExchange(NORMAL_EXCHANGE, true, false, null);
    }

    @Bean
    public Binding normalBinding() {
        return BindingBuilder.bind(lengthQueue()).to(normalExchange()).with(LENGTH_ROUTING_KEY);
    }

    @Bean
    public Binding ttlBinding() {
        return BindingBuilder.bind(ttlQueue()).to(normalExchange()).with(TTL_ROUTING_KEY);
    }

    @Bean
    public Queue deadLetterQueue() {
        return new Queue(DEAD_LETTER_QUEUE, true, false, false, null);
    }

    @Bean
    public DirectExchange deadLetterExchange() {
        return new DirectExchange(DEAD_LETTER_EXCHANGE, true, false, null);
    }

    @Bean
    public Binding dlxBinding() {
        return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange()).with(DEAD_LETTER_ROUTING_KEY);
    }


    // ================================插件实现延迟队列=================================== //
    public static final String DELAY_EXCHANGE = "DelayExchange";
    public static final String DELAY_QUEUE = "DelayQueue";
    public static final String DELAY_ROUTING_KEY = "DelayRoutingKey";
    @Bean
    public Queue delayQueue() {
        return new Queue(DELAY_QUEUE);
    }

    @Bean
    public CustomExchange delayExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(DELAY_EXCHANGE, "x-delayed-message", true, false, args);
    }

    @Bean
    public Binding delayBinding() {
        return BindingBuilder.bind(delayQueue()).to(delayExchange()).with(DELAY_ROUTING_KEY).noargs();
    }

}

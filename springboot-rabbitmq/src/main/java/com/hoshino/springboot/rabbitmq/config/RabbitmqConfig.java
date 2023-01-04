package com.hoshino.springboot.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author huangyuehao
 * @date 2023-01-04
 */
@Configuration
public class RabbitmqConfig {

    // ================================直连模式=================================== //
    public static final String DIRECT_QUEUE_A = "DirectQueue_A";
    public static final String DIRECT_QUEUE_B = "DirectQueue_B";
    public static final String DIRECT_EXCHANGE = "DirectExchange";
    public static final String DIRECT_ROUTING_KEY_A = "DirectRoutingKey_A";
    public static final String DIRECT_ROUTING_KEY_B = "DirectRoutingKey_B";
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
        return new DirectExchange(DIRECT_EXCHANGE, false, false, null);
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
        return new TopicExchange(TOPIC_EXCHANGE);
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
        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
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
//    @Bean
//    public FanoutExchange fanoutExchange() {
//        return new FanoutExchange(DIRECT_EXCHANGE, false, false, null);
//    }

    /**
     * 扇形交换机和队列A绑定.
     */
//    @Bean
//    public Binding bindingFanoutA() {
//        return BindingBuilder.bind(fanoutQueueA()).to(fanoutExchange());
//    }

    /**
     * 扇形交换机和队列B绑定.
     */
//    @Bean
//    public Binding bindingFanoutB() {
//        return BindingBuilder.bind(fanoutQueueB()).to(fanoutExchange());
//    }

    /**
     * 扇形交换机和队列C绑定.
     */
//    @Bean
//    public Binding bindingFanoutC() {
//        return BindingBuilder.bind(fanoutQueueC()).to(fanoutExchange());
//    }

}

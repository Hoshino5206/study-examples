package com.hoshino.rabbitmq.topicMode;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.hoshino.rabbitmq.utils.ConnectionUtil;

/**
 * @author Yy_hoshino
 * @date 2022-07-03 0:41
 */
public class Producer {

    public static final String EXCHANGE_NAME = "test_topic";
    public static final String QUEUE1_NAME = "test_topic_queue1";
    public static final String QUEUE2_NAME = "test_topic_queue2";

    public static void main(String[] args) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        // 从连接中创建通道
        Channel channel = connection.createChannel();

        /*
         * 创建交换机
         * exchangeDeclare(String exchange, BuiltinExchangeType type, boolean durable, boolean autoDelete, boolean internal, Map<String, Object> arguments)
         * 1. exchange:交换机名称
         * 2. type:交换机类型
         *     DIRECT("direct"),
         *     FANOUT("fanout"),
         *     TOPIC("topic"),
         *     HEADERS("headers");
         * 3. durable:是否持久化
         * 4. autoDelete:是否自动删除
         * 5. internal: 内部使用,一般为false
         * 6. arguments: 参数
         */
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC, true, false, false, null);

        /*
         * 创建队列
         * queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments)
         * 1. queue:队列名称
         * 2. durable:是否持久化
         * 3. exclusive:
         *          是否独占，只能有一个消费者监听队列
         *          当connection关闭时,是否删除队列
         * 4. autoDelete: 是否自动删除,当没有consumer时，是否自动删除
         * 5. arguments: 参数
         */
        channel.queueDeclare(QUEUE1_NAME, true, false, false, null);
        channel.queueDeclare(QUEUE2_NAME, true, false, false, null);

        /*
         * 队列绑定交换机
         * queueBind(String queue, String exchange, String routingKey)
         * 1. queue:队列名称
         * 2. exchange:交换机名称
         * 3. routingKey:路由键,绑定规则,
         *      3.1 如果交换机类型为fanout, routingKey设置为""
         *      3.2 如果交换机类型为direct, 根据 RoutingKey 去绑定相应的队列
         *      3.3 如果交换机类型为topic,
         *          * 符号：有且只匹配一个词。比如 a.*可以匹配到"a.b"、"a.c"，但是匹配不了"a.b.c"。
         *          # 符号：匹配一个或多个词。比如"rabbit.#"既可以匹配到"rabbit.a.b"、"rabbit.a"，也可以匹配到"rabbit.a.b.c"。
         */
        channel.queueBind(QUEUE1_NAME, EXCHANGE_NAME, "#.error");
        channel.queueBind(QUEUE1_NAME, EXCHANGE_NAME, "order.*");
        channel.queueBind(QUEUE2_NAME, EXCHANGE_NAME, "*.*");

        String message1 = "主题模式, #.error or order.*  ......";
        String message2 = "主题模式, *.*  ......";
        channel.basicPublish(EXCHANGE_NAME, "aaa.error", null, message1.getBytes());
        channel.basicPublish(EXCHANGE_NAME, "order.bbb", null, message1.getBytes());
        channel.basicPublish(EXCHANGE_NAME, "log.info", null, message2.getBytes());
        System.out.println("发送消息: " + message1);
        System.out.println("发送消息: " + message2);
    }
}

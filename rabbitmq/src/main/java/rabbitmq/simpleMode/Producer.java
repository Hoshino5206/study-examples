package rabbitmq.simpleMode;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import rabbitmq.utils.ConnectionUtil;

/**
 * @author Yy_hoshino
 * @date 2022-06-02 17:35
 */
public class Producer {

    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        /**
         * 声明（创建）队列
         * 1. 队列名称
         * 2. 队列里面的消息是否持久化（磁盘），默认情况消息存储在内存中
         * 3. 该队列是否只供一个消费者进行消费，是否进行消息共享
         * 4. 是否自动删除 最后一个消费者断开连接以后，该队列是否自动删除，true为自动删除
         */
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 消息内容
        String message = "hello world";
        /**
         * 发送一个消息
         * 1. 发送到哪个交换机
         * 2. 路由的key值是哪个 本次是队列的名称
         * 3. 其他参数信息
         * 4. 发送消息的消息体
         */
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println("消息发送完成" + message);
    }
}

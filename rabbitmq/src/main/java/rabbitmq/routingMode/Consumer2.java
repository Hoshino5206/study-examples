package rabbitmq.routingMode;

import com.rabbitmq.client.*;
import rabbitmq.utils.ConnectionUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author Yy_hoshino
 * @date 2022-07-03 0:56
 */
public class Consumer2 {

    public static final String EXCHANGENAME = "test_direct";
    public static final String QUEUE1NAME = "test_direct_queue1";
    public static final String QUEUE2NAME = "test_direct_queue2";

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 接受消息
        Consumer consumer = new DefaultConsumer(channel) {
            /*
             * 回调方法,当收到消息后，会自动执行该方法
             * 1. consumerTag:标识
             * 2. envelope:获取一些信息,交换机,路由key...
             * 3. properties:配置信息
             * 4. body:数据
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("接受信息成功......");
                System.out.println("consumerTag: " + consumerTag);
                System.out.println("envelope: " + envelope);
                System.out.println("properties: " + properties);
                System.out.println("body: " + new String(body, StandardCharsets.UTF_8));
            }
        };

        /*
         * basicConsume(String queue, boolean autoAck, Consumer callback)
         * 1. queue:队列名称
         * 2. autoAck:是否自动确认
         * 3. callback:回调对象
         */
        channel.basicConsume(QUEUE2NAME, true, consumer);
    }
}

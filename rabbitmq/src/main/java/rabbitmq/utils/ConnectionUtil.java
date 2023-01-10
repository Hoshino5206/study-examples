package rabbitmq.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author Yy_hoshino
 * @date 2022-06-02 22:52
 */
public class ConnectionUtil {
    public static Connection getConnection() throws Exception {
        //定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置服务地址和RabbitMQ账号密码
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        // Virtual host
        factory.setVirtualHost("/");
        factory.setUsername("guest");
        factory.setPassword("guest");
        // 通过工厂获取连接
        return factory.newConnection();
    }
}

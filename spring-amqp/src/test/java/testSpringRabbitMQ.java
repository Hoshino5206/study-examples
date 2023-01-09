import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Yy_hoshino
 * @date 2022-07-03 3:11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class testSpringRabbitMQ {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送direct消息,routing路由模式
     */
    @Test
    public void testDirect() {
        rabbitTemplate.convertAndSend("spring_direct_exchange", "direct_routing_key", "spring direct ...");
    }

    /**
     * 发送fanout信息,发布订阅模式
     */
    @Test
    public void testFanout() {
        rabbitTemplate.convertAndSend("spring_fanout_exchange", "", "spring fanout ...");
    }

    /**
     * 发送topic信息,主题(通配符)模式
     */
    @Test
    public void testTopics() {
        rabbitTemplate.convertAndSend("spring_topic_exchange", "log.error", "spring topic ...");
    }

}

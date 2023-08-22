package com.hoshino.springboot.rocketqm.producer;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author huangyuehao
 * @date 2023-02-22
 */
@RestController
public class TopicProducer {

    @Resource
    private RocketMQTemplate rocketmqTemplate;

    @RequestMapping("/rocketmq/topic")
    public void topicProducerMessage() {
        rocketmqTemplate.convertAndSend("topic", "hello world");
    }

}

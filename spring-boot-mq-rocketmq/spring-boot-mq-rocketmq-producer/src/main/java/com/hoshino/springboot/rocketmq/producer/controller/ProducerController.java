package com.hoshino.springboot.rocketmq.producer.controller;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author huangyuehao
 * @date 2023-02-22
 */
@RestController
@RequestMapping("/rocketmq")
public class ProducerController {

    @Resource
    private RocketMQTemplate rocketmqTemplate;

    /**
     * BROADCASTING广播模式
     */
    @RequestMapping("/clustering/sendMessage")
    public void clusteringSendMessage() {
        rocketmqTemplate.convertAndSend("clustering", "hello world, clustering");
    }

    /**
     * BROADCASTING广播模式
     */
    @RequestMapping("/broadcasting/sendMessage")
    public void broadcastingSendMessage() {
        rocketmqTemplate.convertAndSend("broadcasting", "hello world, broadcasting");
    }
}

package com.hoshino.spring.amqp;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * @author Yy_hoshino
 * @date 2022-07-03 12:51
 */
public class QueueListenerFanout1 implements MessageListener {
    @Override
    public void onMessage(Message message) {
        System.out.println("queueListenerFanout1: " + message);
    }
}

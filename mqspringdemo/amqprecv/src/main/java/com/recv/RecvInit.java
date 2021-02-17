package com.recv;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "amqp_queue")
public class RecvInit {
    @RabbitHandler
    public void testRecv(String message) {
        System.out.println("接收到消息" + message);
    }
}

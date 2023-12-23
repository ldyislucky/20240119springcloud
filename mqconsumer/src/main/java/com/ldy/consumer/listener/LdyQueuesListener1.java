package com.ldy.consumer.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class LdyQueuesListener1 {

    //@RabbitListener(queues = {"ldyqueues1"})
    public void getMess(String msg){
        System.out.println("接收到消息："+msg);
    }
    @RabbitListener(queues = {"ldyqueues1"})
    public void c1(String str) throws InterruptedException {
        System.out.println("c1接收到消息："+str);
        Thread.sleep(20);
    }
    @RabbitListener(queues = {"ldyqueues1"})
    public void c2(String str) throws InterruptedException {
        System.err.println("c2接收到消息："+str);
        Thread.sleep(200);
    }
}

package com.ldy.consumer.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ldyFanoutExchange1Listener {
    //@Async//并没有看到多线程
    @RabbitListener(queues = {"ex.queue1"})
    public void getQueue1(String msg) throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+"获取到ex.queue1的消息为："+msg);
        Thread.sleep(20);
    }

    @RabbitListener(queues = {"ex.queue2"})
    public void getQueue2(String msg){
        System.err.println(Thread.currentThread().getName()+"获取到ex.queue2的消息为："+msg);
    }

}

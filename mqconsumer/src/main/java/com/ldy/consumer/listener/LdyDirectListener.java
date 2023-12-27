package com.ldy.consumer.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class LdyDirectListener {
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "dir.queue1"),
            exchange = @Exchange(name = "ldy.dir",type = ExchangeTypes.DIRECT),
            key = {"red","blue"}
    )
    )
    public void m1(String msg){
        System.out.println("获取到dir.queue1的消息为："+msg);
    }
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "dir.queue2"),
            exchange = @Exchange(name = "ldy.dir",type = ExchangeTypes.DIRECT),
            key = {"red","yellow"}
    ))
    public void m2(String msg){
        System.err.println("获取到dir.queue2的消息为："+msg);
    }
}

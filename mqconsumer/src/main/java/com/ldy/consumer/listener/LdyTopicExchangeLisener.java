package com.ldy.consumer.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class LdyTopicExchangeLisener {
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue1"),
            exchange = @Exchange(name = "ldy.topic",type = ExchangeTypes.TOPIC),
            key = "china.#"
    ))
    public void t1(String msg){
        System.out.println("获取到topic.queue1的消息为："+msg);
    }
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue2"),
            exchange = @Exchange(name = "ldy.topic",type = ExchangeTypes.TOPIC),
            key = "#.new"
    ))
    public void t2(String msg){
        System.err.println("获取到topic.queue2的消息为："+msg);
    }
}

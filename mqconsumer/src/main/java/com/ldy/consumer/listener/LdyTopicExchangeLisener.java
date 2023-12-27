package com.ldy.consumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;
@Slf4j
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
        log.info("t2执行");
        System.err.println("获取到topic.queue2的消息为："+msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue3"),
            exchange = @Exchange(name = "ldy.topic",type = ExchangeTypes.TOPIC),
            key = "#.xxx"
    ))
    public void t3(Map<String,String> msg){
        log.info("t3执行");
        System.out.println("获取到topic.queue3的消息为："+msg);
    }
}

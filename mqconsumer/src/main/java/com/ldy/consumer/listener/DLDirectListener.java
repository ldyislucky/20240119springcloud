package com.ldy.consumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class DLDirectListener {
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "dldir.queue",durable = "true"),//durable默认就是true持久化，无需指定
            exchange = @Exchange(name = "ldy.dldir"),
            key = {"dl"}
    ))
    public void m1(Map<String,String> msg){//消费者消费类型要与生产者保持一致，不然收不到消息
        log.error("死信交换机接收到消息："+msg);
    }
}

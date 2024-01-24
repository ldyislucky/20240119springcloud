package com.ldy.consumer.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ErrorDirectListener {
//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue(name = "error.queue"),
//            exchange = @Exchange(name = "ldy.error",type = ExchangeTypes.DIRECT),
//            key = {"errorkey"}
//    ))
//    public void m1(){
//    }
}

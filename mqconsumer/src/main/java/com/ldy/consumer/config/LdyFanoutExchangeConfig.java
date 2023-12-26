package com.ldy.consumer.config;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class LdyFanoutExchangeConfig {
    private final AmqpAdmin amqpAdmin;
    @Bean
    public FanoutExchange ldyFanoutExchange1() throws IOException, TimeoutException {
        FanoutExchange fanoutExchange = new FanoutExchange("ldy.ex");
        amqpAdmin.declareExchange(fanoutExchange);//将交换机声明到mq上
        return fanoutExchange;
    }
    @Bean
    public Queue ldyExQueue1(){
        Queue queue = new Queue("ex.queue1");
        amqpAdmin.declareQueue(queue);
        return queue;
    }
    @Bean
    public Binding binding1(FanoutExchange ldyFanoutExchange1, Queue ldyExQueue1){
        Binding binding = BindingBuilder.bind(ldyExQueue1).to(ldyFanoutExchange1);
        amqpAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public Queue ldyExQueue2(){
        Queue queue = new Queue("ex.queue2");
        amqpAdmin.declareQueue(queue);
        return queue;
    }
    @Bean
    public Binding binding2(FanoutExchange ldyFanoutExchange1, Queue ldyExQueue2){
        Binding binding = BindingBuilder.bind(ldyExQueue2).to(ldyFanoutExchange1);
        amqpAdmin.declareBinding(binding);
        return binding;
    }

}

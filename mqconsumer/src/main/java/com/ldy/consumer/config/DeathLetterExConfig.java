package com.ldy.consumer.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class DeathLetterExConfig {
    @Autowired
    private AmqpAdmin amqpAdmin;
    @Bean
    public DirectExchange getExchange(){
        DirectExchange exchange = new DirectExchange("ldy.ttl");
        amqpAdmin.declareExchange(exchange);
        return exchange;
    }

    @Bean
    public Queue getQueue(){
        Queue queue = QueueBuilder.durable("ttl.queue")
                .ttl(1000)
                .deadLetterExchange("ldy.dldir")
                .deadLetterRoutingKey("dl")
                .build();
        amqpAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    public Binding getBind(){
        Binding binding = BindingBuilder.bind(getQueue()).to(getExchange()).with("ttl.queue");
        amqpAdmin.declareBinding(binding);
        return binding;
    }
}

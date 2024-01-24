package com.ldy.consumer.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ErrorMessageConfig {
    @Autowired
    private  AmqpAdmin amqpAdmin;
    @Bean
    public DirectExchange errorMessageExchange(){
        DirectExchange directExchange = new DirectExchange("ldy.error");
        amqpAdmin.declareExchange(directExchange);
        return directExchange;
    }

    @Bean
    public Queue errorQueue(){
        Queue queue = new Queue("errorkey");
        amqpAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    public Binding errorMessageBinding(){
        Binding binding = BindingBuilder.bind(errorQueue()).to(errorMessageExchange()).with("errorkey");
        amqpAdmin.declareBinding(binding);
        return binding;
    }
    @Bean
    public MessageRecoverer republishMessageRecoverer(RabbitTemplate template){
        return new RepublishMessageRecoverer(template,"ldy.error","errorkey");
    }
}

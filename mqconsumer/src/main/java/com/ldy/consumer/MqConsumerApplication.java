package com.ldy.consumer;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.lang.annotation.*;

@SpringBootApplication
@SpringBootConfiguration
@ComponentScan({"com.ldy.consumer.config","com.ldy.consumer.listener"})
public class MqConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MqConsumerApplication.class,args);
    }
    @Bean//序列化java对象为json格式
    public MessageConverter getMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}

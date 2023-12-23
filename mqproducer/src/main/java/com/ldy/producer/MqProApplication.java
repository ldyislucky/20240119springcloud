package com.ldy.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.ldy.producer.controller"})
@SpringBootApplication
public class MqProApplication {
    public static void main(String[] args) {
        SpringApplication.run(MqProApplication.class,args);
    }
}

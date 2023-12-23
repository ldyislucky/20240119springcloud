package com.ldy.producer.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/producer")
@RequiredArgsConstructor
public class ProducerController {
    private final RabbitTemplate rabbitTemplate;
    @GetMapping("/p1")
    public void t1(){
        log.info("模式一生产队列");
        String queue = "ldyqueues1";
        String str = "holle world!";
        rabbitTemplate.convertAndSend(queue,str);
    }

    @GetMapping("/p2")
    public void t2() throws InterruptedException {
        log.info("模式二生产队列");
        String queue = "ldyqueues1";
        String str = "holle world! -";
        for (int i = 0; i < 50; i++) {
            str= str+i;
            rabbitTemplate.convertAndSend(queue,str);
            Thread.sleep(20);
        }
    }
}

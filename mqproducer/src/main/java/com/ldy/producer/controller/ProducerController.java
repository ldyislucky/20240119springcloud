package com.ldy.producer.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        log.info("模式一生产消息到队列");
        String queue = "ldyqueues1";
        String str = "holle world!";
        rabbitTemplate.convertAndSend(queue,str);
    }

    @GetMapping("/p2")
    public void t2() throws InterruptedException {
        log.info("模式二生产消息到队列");
        String queue = "ldyqueues1";
        String str;
        for (int i = 0; i < 50; i++) {
            str = "holle world! -"+i;
            rabbitTemplate.convertAndSend(queue,str);
            Thread.sleep(20);
        }
    }
    @GetMapping("/pudsub")
    public void t3(){
        log.info("发布订阅模式生产消息到队列");
        String exfanout = "ldy.ex";
        String str;
        for (int i = 0; i < 50; i++) {
            str = "holle world! -"+i;
            rabbitTemplate.convertAndSend(exfanout,"",str);
        }
    }
    @GetMapping("/dir/{key}")
    public void t4(@PathVariable("key")String key){
        log.info("执行t4");
        String direx = "ldy.dir";
        String str;
        for (int i = 0; i < 50; i++) {
            str = "holle world! -"+key+i;
            rabbitTemplate.convertAndSend(direx,key,str);
        }
    }
    @GetMapping("/topic/{key}")
    public void t5(@PathVariable("key")String key){
        log.info("执行t5");
        String direx = "ldy.topic";
        String str;
        for (int i = 0; i < 50; i++) {
            str = "holle world! -"+key+i;
            rabbitTemplate.convertAndSend(direx,key,str);
        }
    }
}

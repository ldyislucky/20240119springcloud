package org.quarzboot.ldy.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Component
public class MyJob3 {
    @Scheduled(fixedDelay = 1000)
    public void run() throws InterruptedException {
        log.error(Thread.currentThread().getName()+"===开始执行注解");
        System.out.println("It's time to run :" + new Date());
        log.error(Thread.currentThread().getName()+"===结束执行注解");
    }
}

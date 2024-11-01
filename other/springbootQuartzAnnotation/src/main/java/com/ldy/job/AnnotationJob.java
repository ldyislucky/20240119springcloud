package com.ldy.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class AnnotationJob {
    @Scheduled(fixedDelay = 2000)
    public void run() throws InterruptedException {
        log.info(Thread.currentThread().getName()+"===开始执行注解");
        System.out.println("It's time to run :" + new Date());
        log.info(Thread.currentThread().getName()+"===结束执行注解");
    }
}

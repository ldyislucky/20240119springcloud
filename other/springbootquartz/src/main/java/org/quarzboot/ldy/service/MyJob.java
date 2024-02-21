package org.quarzboot.ldy.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
@Slf4j
@Service
public class MyJob {
    @Scheduled(cron="0/1 * * * * ?")
    public void run() throws InterruptedException {
        log.error(Thread.currentThread().getName()+"===开始执行");
        System.out.println("It's time to run :" + new Date());
        Thread.sleep(6000);
        log.error(Thread.currentThread().getName()+"===结束执行");
    }
}

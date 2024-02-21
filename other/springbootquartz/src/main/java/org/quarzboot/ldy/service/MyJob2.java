package org.quarzboot.ldy.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
@Slf4j
@Service
public class MyJob2 {
    //@Scheduled()
    public void run() throws InterruptedException {
        log.warn(Thread.currentThread().getName()+"===开始执行222");
        System.out.println("It's time to run :" + new Date());
        Thread.sleep(2000);
        log.warn(Thread.currentThread().getName()+"===结束执行222");
    }
}

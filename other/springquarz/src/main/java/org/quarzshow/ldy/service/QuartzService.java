package org.quarzshow.ldy.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class QuartzService {
    public void run() throws InterruptedException {
        log.error(Thread.currentThread().getName()+"===开始执行");
        System.out.println("It's time to run :" + new Date());
        log.error(Thread.currentThread().getName()+"===结束执行");
    }
}

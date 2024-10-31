package org.quarzboot.ldy.controller;


import lombok.extern.slf4j.Slf4j;
import org.quarzboot.ldy.service.MyJob;
import org.quarzboot.ldy.service.MyJob3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("quartzboot")
public class ManyScheduled {
    @Autowired
    private MyJob3 myJob;

    @GetMapping("/t1")
    public void run() throws InterruptedException {
        /**
         * 加载配置以触发定时任务
         */
        new ClassPathXmlApplicationContext("classpath:application.xml");
    }

    @GetMapping("/zhujie")
    public void run2() throws InterruptedException {
        myJob.run();
    }

}

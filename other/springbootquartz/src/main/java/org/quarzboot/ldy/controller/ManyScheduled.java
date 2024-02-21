package org.quarzboot.ldy.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("quartzboot")
public class ManyScheduled {
    @GetMapping("/t1")
    public void run() throws InterruptedException {
        /**
         * 加载配置以触发定时任务
         */
        new ClassPathXmlApplicationContext("classpath:application.xml");
    }

}

package org.quarzshow.ldy.controller;

import lombok.extern.slf4j.Slf4j;
import org.quarzshow.ldy.service.QuartzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("quartz")
@RestController
public class QuartzController {
    @GetMapping("/t1")
    public void run() throws InterruptedException {
        /**
         * 加载配置以触发定时任务
         */
        new ClassPathXmlApplicationContext("classpath:application.xml");
    }
}

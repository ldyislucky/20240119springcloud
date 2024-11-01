package com.ldy.job;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.dubbo.config.annotation.DubboReference;
import org.dubboapi.ldy.user.DubboQuartzUserApi;
import org.dubbopojo.ldy.QuartzUser;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.xml.stream.Location;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

@Slf4j
@Component
public class AnnotationJob {

    @DubboReference
    DubboQuartzUserApi dubboQuartzUserApi;

    @Scheduled(fixedDelay = 5000)
    public void run() throws InterruptedException {
        log.info(Thread.currentThread().getName()+"===开始执行注解");
        QuartzUser quartzUser = new QuartzUser();
        quartzUser.setUsername("小明");
        quartzUser.setCreated_at(LocalDateTime.now());
        quartzUser.setUpdated_at(LocalDateTime.now());

        ArrayList<QuartzUser> quartzUsers = new ArrayList<>();
        quartzUsers.add(quartzUser);

        dubboQuartzUserApi.addQuartzUser(quartzUsers);


        log.info(Thread.currentThread().getName()+"===结束执行注解");
    }
}

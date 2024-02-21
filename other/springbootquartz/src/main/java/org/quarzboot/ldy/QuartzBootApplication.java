package org.quarzboot.ldy;

import com.ldy.feign.config.DefaultFeignConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@MapperScan("org.quarzboot.ldy.mapper")
@ComponentScan({"org.quarzboot.ldy.mapper","org.quarzboot.ldy.service","org.quarzboot.ldy.controller",
        "org.quarzboot.ldy.config"})
@EnableFeignClients(//clients = {UserClients.class},
        defaultConfiguration = DefaultFeignConfiguration.class,
        basePackages = {"com.ldy.feign.client"}
        )//clients = UserClients.class指的是扫描引用的依赖中的类为bean,defaultConfiguration可以指定多个配置类
public class QuartzBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuartzBootApplication.class,args);
    }
}

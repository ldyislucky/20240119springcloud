package org.quarzshow.ldy;

import com.ldy.feign.config.DefaultFeignConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@MapperScan("org.quarzshow.ldy.mapper")
@ComponentScan({"org.quarzshow.ldy.mapper","org.quarzshow.ldy.service","org.quarzshow.ldy.controller",
        "org.quarzshow.ldy.config"})
@EnableFeignClients(//clients = {UserClients.class},
        defaultConfiguration = DefaultFeignConfiguration.class,
        basePackages = {"com.ldy.feign.client"}
        )//clients = UserClients.class指的是扫描引用的依赖中的类为bean,defaultConfiguration可以指定多个配置类
public class QuartzApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuartzApplication.class,args);
    }
}

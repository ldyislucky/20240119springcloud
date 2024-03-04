package org.dubbouser.ldy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("org.dubbouser.ldy.mapper")
@ComponentScan({"org.dubbouser.ldy.mapper","org.dubbouser.ldy.service","org.dubbouser.ldy.controller"})
public class DubboUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(DubboUserApplication.class,args);
    }
}

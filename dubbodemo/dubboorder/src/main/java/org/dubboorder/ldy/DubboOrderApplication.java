package org.dubboorder.ldy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("org.dubboorder.ldy.mapper")
@ComponentScan({"org.dubboorder.ldy.mapper","org.dubboorder.ldy.service","org.dubboorder.ldy.controller","org.dubboorder.ldy.entity"})
public class DubboOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(DubboOrderApplication.class,args);
    }
}

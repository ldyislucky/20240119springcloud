package com.ldy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.ldy.config","com.ldy.controller","com.ldy.service","com.ldy.entity","com.ldy.chunk.processor"})  //如果注释掉@Autowired就没办法自动装配了
@MapperScan("com.ldy.mapper")
@SpringBootApplication
public class SpringBatchApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBatchApplication.class, args);
    }
}

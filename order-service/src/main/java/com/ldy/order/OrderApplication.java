package com.ldy.order;

import com.ldy.feign.client.UserClients;
import com.ldy.feign.config.DefaultFeignConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@MapperScan("com.ldy.order.mapper")
@ComponentScan({"com.ldy.order.mapper","com.ldy.order.service","com.ldy.order.entity","com.ldy.order.controller",
        "com.ldy.order.config","com.ldy.order.sentinel"})
@EnableFeignClients(//clients = {UserClients.class},
        defaultConfiguration = DefaultFeignConfiguration.class,
        basePackages = {"com.ldy.feign.client"}
        )//clients = UserClients.class指的是扫描引用的依赖中的类为bean,defaultConfiguration可以指定多个配置类
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class,args);
    }
}

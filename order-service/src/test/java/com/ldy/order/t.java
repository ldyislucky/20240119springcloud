package com.ldy.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = OrderApplication.class)
public class t {
    @Value("${pattern.dateformat}")
    private String nacos;
    @Test
    public void t1(){
        System.out.println(nacos);
    }
}

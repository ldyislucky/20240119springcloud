package com.ldy.order;

import com.alibaba.fastjson.JSON;
import com.ldy.order.entity.OrderTbl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = OrderApplication.class)
public class t {
    //@Value("${pattern.dateformat}")
    private String nacos;
    @Test
    public void t1(){
        System.out.println(nacos);
    }
    @Test
    public void t2(){
        OrderTbl orderTbl = new OrderTbl();
        orderTbl.setUserId("xiaoli");
        orderTbl.setMoney(100);
        orderTbl.setCommodityCode("100202003032041");
        orderTbl.setCount(1);
        String jsonString = JSON.toJSONString(orderTbl);
        System.out.println(jsonString);
    }
}

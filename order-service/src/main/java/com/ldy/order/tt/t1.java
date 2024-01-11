package com.ldy.order.tt;

import com.alibaba.fastjson.JSON;
import com.ldy.order.entity.OrderTbl;

public class t1 {
    public static void main(String[] args) {
        OrderTbl orderTbl = new OrderTbl();
        orderTbl.setUserId("xiaoli");
        orderTbl.setMoney(100);
        orderTbl.setCommodityCode("100202003032041");
        orderTbl.setCount(1);
        String jsonString = JSON.toJSONString(orderTbl);
        System.out.println(jsonString);
    }
}

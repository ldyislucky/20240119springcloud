package com.ldy.order.controller;


import com.ldy.feign.client.StorageClient;
import com.ldy.feign.client.UserClients;
import com.ldy.feign.entity.AccountTbl;
import com.ldy.feign.entity.Storage;
import com.ldy.order.entity.OrderTbl;
import com.ldy.order.general.R;
import com.ldy.order.service.IOrderTblService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2024-01-10
 */
@RestController
@RequestMapping("/order-tbl")
@RequiredArgsConstructor
public class OrderTblController {
    @Autowired
    IOrderTblService iOrderTblService;
    private final UserClients userClients;
    private final StorageClient storageClient;
    @PostMapping("/ordertx")
    public R<String> createOrder(@RequestBody OrderTbl orderTbl){
        iOrderTblService.save(orderTbl);
        //提取用户类
        AccountTbl accountTbl = new AccountTbl();
        accountTbl.setUserId(orderTbl.getUserId());
        accountTbl.setMoney(orderTbl.getMoney());
        //提取仓库类
        Storage storage = new Storage();
        storage.setCommodityCode(orderTbl.getCommodityCode());
        storage.setCount(orderTbl.getCount());
        //扣款
        userClients.updateMoney(accountTbl);
        //更新货物数量
        storageClient.updateGoods(storage);
        return R.success("下单成功!");
    }

}

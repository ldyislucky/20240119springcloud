package com.ldy.order.controller;


import com.ldy.feign.client.StorageClient;
import com.ldy.feign.client.UserClients;
import com.ldy.feign.entity.AccountTbl;
import com.ldy.feign.entity.Storage;
import com.ldy.order.entity.OrderTbl;
import com.ldy.order.general.R;
import com.ldy.order.service.IOrderTblService;
import feign.FeignException;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2024-01-10
 */
@Slf4j
@RestController
@RequestMapping("order-tbl")
@RequiredArgsConstructor
public class OrderTblController {
    @Autowired
    IOrderTblService iOrderTblService;
    private final UserClients userClients;
    private final StorageClient storageClient;
    @PostMapping("/ordertx")
    @GlobalTransactional//开启微服务全局事务，只需要在事务开始的入口处添加即可
    public R<String> createOrder(@RequestBody OrderTbl orderTbl){
        log.info("创建订单！");
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
    @PostMapping("/ordertx1")
    @GlobalTransactional//开启微服务全局事务，只需要在事务开始的入口处添加即可
    public R<String> createOrder1(@RequestBody OrderTbl orderTbl){
        log.info("创建订单！");
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
        try {
            //扣款
            userClients.updateMoney1(accountTbl);
            //更新货物数量
            storageClient.updateGoods(storage);
        }catch (FeignException e){
            log.error("下单失败，原因:{}", e.contentUTF8(), e);
            throw new RuntimeException(e.contentUTF8(), e);
        }
            String s = userClients.updateMoney1(accountTbl);
            return R.success(s);
    }

}

package com.ldy.user.controller;


import com.alibaba.fastjson.JSON;
import com.ldy.user.entity.AccountTbl;
import com.ldy.user.service.AccountTccService;
import com.ldy.user.service.IAccountTblService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
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
@RequestMapping("/account-tbl")
public class AccountTblController {
    @Autowired
    IAccountTblService accountTblService;
    @Autowired
    AccountTccService accountTccService;
    @PutMapping("/pay")
    public String updateMoney(@RequestBody AccountTbl user){
        AccountTbl userOld = accountTblService.lambdaQuery()
                .eq(AccountTbl::getUserId, user.getUserId())
                .one();
        Integer money = user.getMoney();
        user.setMoney(userOld.getMoney()-money);
        accountTblService
                .lambdaUpdate()
                .eq(AccountTbl::getUserId,user.getUserId())
                .update(user);
        return "用户余额扣除成功！";
    }
    @PutMapping("/pay1")
    public String pay1(@RequestBody AccountTbl user){
        accountTccService.pay(user.getUserId(), user.getMoney());
        return "用户余额扣除成功！";
    }
}

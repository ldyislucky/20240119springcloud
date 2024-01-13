package com.ldy.user.service.impl;

import com.ldy.user.entity.AccountFreezeTbl;
import com.ldy.user.entity.AccountTbl;
import com.ldy.user.eum.AccountFreezeTblStatus;
import com.ldy.user.service.AccountTccService;
import com.ldy.user.service.IAccountFreezeTblService;
import com.ldy.user.service.IAccountTblService;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountTccServiceImpl implements AccountTccService {
    private final IAccountTblService  iAccountTblService;
    private final IAccountFreezeTblService iAccountFreezeTblService;
    @Override
    public void pay(String userid, int money) {
        AccountTbl accountTbl = new AccountTbl();
        accountTbl.setUserId(userid);
        accountTbl.setMoney(money);
        //扣除余额
        iAccountTblService.dudect(accountTbl);
        //余额冻结表添加信息
        AccountFreezeTbl accountFreezeTbl = new AccountFreezeTbl();
        accountFreezeTbl.setUserId(userid);
        accountFreezeTbl.setFreezeMoney(money);
        accountFreezeTbl.setState(AccountFreezeTblStatus.TRY);
        iAccountFreezeTblService.save(accountFreezeTbl);
    }

    @Override
    public boolean confirm(BusinessActionContext ctx) {
        String xid = ctx.getXid();
        return iAccountFreezeTblService.removeById(xid);
    }

    @Override
    public boolean cancle(BusinessActionContext ctx) {
        //撤销余额扣减
        //更新冻结表的冻结金额和状态
        return false;
    }
}

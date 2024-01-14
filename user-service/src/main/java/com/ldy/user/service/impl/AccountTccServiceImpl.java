package com.ldy.user.service.impl;

import com.ldy.user.entity.AccountFreezeTbl;
import com.ldy.user.entity.AccountTbl;
import com.ldy.user.eum.AccountFreezeTblStatus;
import com.ldy.user.service.AccountTccService;
import com.ldy.user.service.IAccountFreezeTblService;
import com.ldy.user.service.IAccountTblService;
import io.seata.core.context.RootContext;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountTccServiceImpl implements AccountTccService {
    private final IAccountTblService  iAccountTblService;
    private final IAccountFreezeTblService iAccountFreezeTblService;
    @Transactional
    @Override
    public void pay(String userid, int money) {
        String xid = RootContext.getXID();
        AccountTbl accountTbl = new AccountTbl();
        accountTbl.setUserId(userid);
        accountTbl.setMoney(money);
        //扣除余额
        iAccountTblService.dudect(accountTbl);
        //余额冻结表添加信息
        AccountFreezeTbl accountFreezeTbl = new AccountFreezeTbl();
        accountFreezeTbl.setXid(xid);
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

    @Transactional
    @Override
    public boolean cancle(BusinessActionContext ctx) {
        //获取冻结数据
        String xid = ctx.getXid();
        AccountFreezeTbl accountFreezeTbl = iAccountFreezeTblService.getById(xid);
        AccountTbl accountTbl = new AccountTbl();
        accountTbl.setUserId(accountFreezeTbl.getUserId());
        accountTbl.setMoney(accountFreezeTbl.getFreezeMoney());
        //撤销余额扣减
        iAccountTblService.undoDudect(accountTbl);
        //更新冻结表的冻结金额和状态
        boolean b = iAccountFreezeTblService.lambdaUpdate()
                .set(AccountFreezeTbl::getFreezeMoney, 0)
                .set(AccountFreezeTbl::getState, AccountFreezeTblStatus.CANCEL)
                .eq(AccountFreezeTbl::getXid,xid)
                .update();
        return b;
    }
}

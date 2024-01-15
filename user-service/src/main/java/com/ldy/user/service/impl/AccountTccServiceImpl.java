package com.ldy.user.service.impl;

import com.ldy.user.entity.AccountFreezeTbl;
import com.ldy.user.entity.AccountTbl;
import com.ldy.user.mapper.AccountFreezeTblMapper;
import com.ldy.user.mapper.AccountTblMapper;
import com.ldy.user.service.AccountTccService;
import io.seata.core.context.RootContext;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Slf4j
@Service
@RequiredArgsConstructor
public class AccountTccServiceImpl implements AccountTccService {
    private final AccountTblMapper accountTblMapper;
    private final AccountFreezeTblMapper freezeTblMapper;
    @Transactional
    @Override
    public void pay(String userid, int money) {
        String xid = RootContext.getXID();
        log.info("pay的xid："+xid);
        AccountTbl accountTbl = new AccountTbl();
        //扣除余额
        accountTblMapper.deduct(userid,money);
        //余额冻结表添加信息
        AccountFreezeTbl accountFreezeTbl = new AccountFreezeTbl();
        accountFreezeTbl.setXid(xid);
        accountFreezeTbl.setUserId(userid);
        accountFreezeTbl.setFreezeMoney(money);
        accountFreezeTbl.setState(AccountFreezeTbl.State.TRY);
        freezeTblMapper.insert(accountFreezeTbl);
    }

    @Override
    public boolean confirm(BusinessActionContext ctx) {
        String xid = ctx.getXid();
        log.info("confirm的xid："+xid);
        int i = freezeTblMapper.deleteById(xid);
        return i==1;
    }

    @Transactional
    @Override
    public boolean cancle(BusinessActionContext ctx) {
        //获取冻结数据
        String xid = ctx.getXid();
        AccountFreezeTbl accountFreezeTbl = freezeTblMapper.selectById(xid);
        //撤销余额扣减
        accountTblMapper.undoDeduct(accountFreezeTbl.getUserId(), accountFreezeTbl.getFreezeMoney());
        //更新冻结表的冻结金额和状态
        accountFreezeTbl.setFreezeMoney(0);
        accountFreezeTbl.setState(AccountFreezeTbl.State.CANCEL);
        int i = freezeTblMapper.updateById(accountFreezeTbl);
        return i==1;
    }
}

package com.ldy.user.service.impl;

import com.ldy.user.entity.AccountFreezeTbl;
import com.ldy.user.entity.AccountTbl;
import com.ldy.user.mapper.AccountFreezeTblMapper;
import com.ldy.user.mapper.AccountTblMapper;
import com.ldy.user.service.AccountTccService;
import com.ldy.user.service.IAccountFreezeTblService;
import io.seata.core.context.RootContext;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.spring.annotation.GlobalTransactional;
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
    private final IAccountFreezeTblService freezeTblService;

    @Transactional
    @Override
    public void pay(String userid, int money) {
        String xid = RootContext.getXID();
        log.info("pay的xid："+xid);
        AccountFreezeTbl tbl = freezeTblMapper.selectById(xid);
        if (tbl!=null){
            return;
        }
        //扣除余额
        accountTblMapper.deduct(userid,money);
        //新增冻结金额数据
        AccountFreezeTbl freezeTbl = new AccountFreezeTbl();
        freezeTbl.setXid(xid);
        freezeTbl.setUserId(userid);
        freezeTbl.setFreezeMoney(money);
        freezeTbl.setState(AccountFreezeTbl.State.TRY);
        freezeTblMapper.insert(freezeTbl);
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
        log.info("cancle的xid："+xid);
        AccountFreezeTbl accountFreezeTbl = freezeTblMapper.selectById(xid);
        String userid = ctx.getActionContext().get("userid").toString();
        //空回滚逻辑
        if(accountFreezeTbl==null){
            AccountFreezeTbl freezeTbl = new AccountFreezeTbl();
            freezeTbl.setXid(xid);
            freezeTbl.setUserId(userid);
            freezeTbl.setFreezeMoney(0);
            freezeTbl.setState(AccountFreezeTbl.State.CANCEL);
            freezeTblMapper.insert(freezeTbl);
        }
        if (accountFreezeTbl.getState()==AccountFreezeTbl.State.CANCEL){
            return true;
        }

        //撤销余额扣减
        accountTblMapper.undoDeduct(accountFreezeTbl.getUserId(), accountFreezeTbl.getFreezeMoney());
        //更新冻结表的冻结金额和状态
        accountFreezeTbl.setFreezeMoney(0);
        accountFreezeTbl.setState(AccountFreezeTbl.State.CANCEL);
        int i = freezeTblMapper.updateById(accountFreezeTbl);
        return i==1;
    }
}

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

    /**
     * 本例使用的是冻结表的方式解决空回滚和空悬挂，也可以通过打标志的方法解决这两个问题
     */
    @Transactional
    @Override
    public void pay(String userid, int money) {
        String xid = RootContext.getXID();
        log.info("pay的xid："+xid);
        /**
         * 客户端调用pay()方法超时，pay()方法未执行，客户端认为调用失败开始触发回滚逻辑，就是空回滚
         * 空悬挂指 Try 方法在 Cancel 方法之后执行（例如 Try 因网络延迟在全局事务回滚后才到达）
         * 如果空悬挂的话，冻结表会有一条空回滚的记录，空回滚记录是后面自己实现的逻辑
         */
        AccountFreezeTbl tbl = freezeTblMapper.selectById(xid);
        //做个判断防止空悬挂
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
        /**
         * 客户端调用pay()方法超时，pay()方法未执行，客户端认为调用失败开始触发回滚逻辑，
         * 就是空回滚，这种情况下冻结表是没有记录的
         */
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
            //不能直接改成return true;需要在冻结表留痕，用于前面空悬挂的判断
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

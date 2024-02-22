package com.ldy.user.service.impl;

import com.ldy.user.entity.AccountTbl;
import com.ldy.user.mapper.AccountTblMapper;
import com.ldy.user.service.IAccountTblService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2024-01-10
 */
@Service
public class AccountTblServiceImpl extends ServiceImpl<AccountTblMapper, AccountTbl> implements IAccountTblService {

    @Override
    public void dudect(AccountTbl accountTbl) {
        AccountTbl accountTblOld = lambdaQuery().eq(AccountTbl::getUserId, accountTbl.getUserId())
                .one();
        accountTblOld.setMoney(accountTblOld.getMoney()-accountTbl.getMoney());
        //这两步之间线程可能不是安全的，可以再数据库中添加乐观锁版本号，并且在类中乐观锁字段
        // 配置mybatisplus的@Version注解以保证整个事务的安全
        lambdaUpdate().update(accountTblOld);
    }

    @Override
    public void undoDudect(AccountTbl accountTbl) {
        AccountTbl accountTblOld = lambdaQuery().eq(AccountTbl::getUserId, accountTbl.getUserId())
                .one();
        accountTblOld.setMoney(accountTblOld.getMoney()+accountTbl.getMoney());
        //这两步之间线程可能不是安全的，可以再数据库中添加乐观锁版本号，并且在类中乐观锁字段
        // 配置mybatisplus的@Version注解以保证整个事务的安全
        lambdaUpdate().update(accountTblOld);
    }
}

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
        lambdaUpdate().eq(AccountTbl::getUserId,accountTbl.getUserId())
                .set(AccountTbl::getMoney,accountTblOld.getMoney()-accountTbl.getMoney())
                .update();
    }

    @Override
    public void undoDudect(AccountTbl accountTbl) {
        AccountTbl accountTblOld = lambdaQuery().eq(AccountTbl::getUserId, accountTbl.getUserId())
                .one();
        lambdaUpdate().eq(AccountTbl::getUserId,accountTbl.getUserId())
                .set(AccountTbl::getMoney,accountTblOld.getMoney()+accountTbl.getMoney())
                .update();
    }
}

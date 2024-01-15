package com.ldy.user.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

import java.lang.reflect.Parameter;

@LocalTCC
public interface AccountTccService {
    @TwoPhaseBusinessAction(name = "pay",commitMethod = "confirm",rollbackMethod = "cancle")//2阶段业务活动
    void pay(@BusinessActionContextParameter(paramName = "userid") String userid,
             @BusinessActionContextParameter(paramName = "money") int money);
    boolean confirm(BusinessActionContext ctx);
    boolean cancle(BusinessActionContext ctx);
}

package com.ldy.feign.client.fallback;


import com.ldy.feign.client.UserClients;

import com.ldy.feign.entity.AccountTbl;
import com.ldy.feign.entity.TbUser;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * 夸服务调用友好提示异常
 */
@Slf4j
public class UserClientFallbackFactory implements FallbackFactory<UserClients> {
    @Override
    public UserClients create(Throwable throwable) {
        return new UserClients() {
            @Override
            public TbUser getById(Long id) {
                log.error("查询用户异常", throwable);
                return new TbUser();
            }

            @Override
            public String updateMoney(AccountTbl accountTbl) {
                log.error("支付异常", throwable);
                return "支付异常";
            }

            @Override
            public String updateMoney1(AccountTbl accountTbl) {
                log.error("支付异常", throwable);
                return "支付异常";
            }
        };
    }
}

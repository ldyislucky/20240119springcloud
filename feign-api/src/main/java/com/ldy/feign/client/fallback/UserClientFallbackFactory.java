package com.ldy.feign.client.fallback;


import com.ldy.feign.client.UserClients;

import com.ldy.feign.entity.TbUser;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

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

        };
    }
}

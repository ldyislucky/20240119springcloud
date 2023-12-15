package com.ldy.order.controller;

import com.ldy.order.entity.TbUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("user-service")//服务名称前边不要加“/”
public interface UserClients {
    @GetMapping("/tb-user/user/{id}")
    TbUser getById(@PathVariable("id") Long id);

}

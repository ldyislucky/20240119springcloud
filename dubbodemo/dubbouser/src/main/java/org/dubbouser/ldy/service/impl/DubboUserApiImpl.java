package org.dubbouser.ldy.service.impl;

import org.apache.dubbo.config.annotation.DubboService;
import org.dubboapi.ldy.user.DubboUserApi;
import org.dubbopojo.ldy.R;
import org.dubbopojo.ldy.TbUser;
import org.dubbouser.ldy.service.ITbUserService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class DubboUserApiImpl implements DubboUserApi {
    @Autowired
    private ITbUserService iTbUserService;
    @Override
    public R<TbUser> findUserById(long id) {
        TbUser tbUser = iTbUserService.getById(id);
        return R.success(tbUser);
    }
}

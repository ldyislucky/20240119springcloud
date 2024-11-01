package org.dubbouser.ldy.service.dubbo;

import org.apache.dubbo.config.annotation.DubboService;
import org.dubboapi.ldy.user.DubboQuartzUserApi;
import org.dubbopojo.ldy.QuartzUser;
import org.dubbouser.ldy.service.QuartzUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class DubboQuartzUserApiImp implements DubboQuartzUserApi {

    @Autowired
    QuartzUserService quartzUserService;
    @Override
    public void addQuartzUser(List<QuartzUser> quartzUsers) {
        quartzUserService.saveBatch(quartzUsers);
    }
}

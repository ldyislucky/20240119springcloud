package org.dubbouser.ldy.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.apache.dubbo.config.annotation.DubboService;
import org.dubbopojo.ldy.R;
import org.dubbopojo.ldy.TbUser;
import org.dubbouser.ldy.mapper.TbUserMapper;
import org.dubbouser.ldy.service.ITbUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2023-12-10
 */
@DubboService
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUser> implements ITbUserService {

    @Override
    public R<TbUser> findUserById(long id) {
        TbUser tbUser = new TbUser();
        tbUser.setId(666L);
        tbUser.setAddress("测试");
        tbUser.setUsername("测试");
        return R.success(tbUser);
    }
}

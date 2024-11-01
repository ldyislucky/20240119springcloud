package org.dubbouser.ldy.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dubbopojo.ldy.QuartzUser;
import org.dubbouser.ldy.mapper.QuartzUserMapper;
import org.dubbouser.ldy.service.QuartzUserService;
import org.springframework.stereotype.Service;

@Service
public class QuartzUserServiceImp extends ServiceImpl<QuartzUserMapper, QuartzUser> implements QuartzUserService{

}

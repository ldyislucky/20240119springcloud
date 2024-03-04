package org.dubboorder.ldy.service.impl;

import com.xiaoleilu.hutool.bean.BeanUtil;
import org.apache.dubbo.config.annotation.DubboReference;
import org.dubboapi.ldy.user.DubboUserApi;
import org.dubboorder.ldy.entity.DTO.TbOrderDTO;
import org.dubboorder.ldy.entity.TbOrder;
import org.dubboorder.ldy.mapper.TbOrderMapper;
import org.dubboorder.ldy.service.ITbOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dubbopojo.ldy.R;
import org.dubbopojo.ldy.TbUser;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2024-03-04
 */
@Service
public class TbOrderServiceImpl extends ServiceImpl<TbOrderMapper, TbOrder> implements ITbOrderService {

    @DubboReference
    private DubboUserApi dubboUserApi;

    @Override
    public R<TbOrderDTO> getTbOrderDTO(long id) {
        TbOrderDTO tbOrderDTO = new TbOrderDTO();
        TbOrder tbOrder = getById(id);
        BeanUtil.copyProperties(tbOrder,tbOrderDTO);
        TbUser tbUser = dubboUserApi.findUserById(tbOrder.getUserId()).getData();
        tbOrderDTO.setTbUser(tbUser);
        return R.success(tbOrderDTO);
    }
}

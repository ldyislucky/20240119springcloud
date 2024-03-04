package org.dubboorder.ldy.service;

import org.dubboorder.ldy.entity.DTO.TbOrderDTO;
import org.dubboorder.ldy.entity.TbOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import org.dubbopojo.ldy.R;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2024-03-04
 */
public interface ITbOrderService extends IService<TbOrder> {

    R<TbOrderDTO> getTbOrderDTO(long id);

}

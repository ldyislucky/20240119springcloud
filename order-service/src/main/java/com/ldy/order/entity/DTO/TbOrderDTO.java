package com.ldy.order.entity.DTO;

import com.ldy.feign.entity.TbUser;
import com.ldy.order.entity.TbOrder;

import lombok.Data;

@Data
public class TbOrderDTO extends TbOrder {
    private TbUser tbUser;
}

package com.ldy.order.entity.DTO;

import com.ldy.order.entity.TbOrder;
import com.ldy.order.entity.TbUser;
import lombok.Data;

@Data
public class TbOrderDTO extends TbOrder {
    private TbUser tbUser;
}

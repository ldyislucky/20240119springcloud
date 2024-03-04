package org.dubboorder.ldy.entity.DTO;

import lombok.Data;
import org.dubboorder.ldy.entity.TbOrder;
import org.dubbopojo.ldy.TbUser;
@Data
public class TbOrderDTO extends TbOrder {
    private TbUser tbUser;
}

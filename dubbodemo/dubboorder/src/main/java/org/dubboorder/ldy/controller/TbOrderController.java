package org.dubboorder.ldy.controller;


import org.dubboorder.ldy.entity.DTO.TbOrderDTO;
import org.dubboorder.ldy.service.ITbOrderService;
import org.dubbopojo.ldy.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2024-03-04
 */
@RestController
@RequestMapping("dubbo-order")
public class TbOrderController {

    @Autowired
    private ITbOrderService iTbOrderService;
    @GetMapping("TbOrderDTOById/{id}")
    public R<TbOrderDTO> t1(@PathVariable("id")long id){
        return iTbOrderService.getTbOrderDTO(id);
    }

}

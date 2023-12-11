package com.ldy.order.controller;


import com.ldy.order.entity.TbOrder;
import com.ldy.order.general.R;
import com.ldy.order.service.ITbOrderService;
import lombok.RequiredArgsConstructor;
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
 * @since 2023-12-10
 */
@RestController
@RequestMapping("/tb-order")
@RequiredArgsConstructor
public class TbOrderController {
    private final ITbOrderService iTbOrderService;
    @GetMapping("/{id}")
    public R<TbOrder> getOrder(@PathVariable("id") Long id){
        TbOrder tbOrder = iTbOrderService.getById(id);
        return R.success(tbOrder);
    }

}

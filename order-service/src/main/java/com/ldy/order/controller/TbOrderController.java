package com.ldy.order.controller;


import com.ldy.order.config.NacosConfig;
import com.ldy.order.entity.TbOrder;
import com.ldy.order.general.R;
import com.ldy.order.service.ITbOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
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
//@RefreshScope//这种热更新的方式应该是只适用于这个类，可能没有很方便
public class TbOrderController {

    @Value("${nacos.pattern.dateformat}")
    private String nacos;

    /**
     * 通过@ConfigurationProperties(prefix = "nacos.pattern")的方式热更新，推荐的方式
     */
    private final NacosConfig nacosConfig;

    private final ITbOrderService iTbOrderService;
    @GetMapping("/{id}")
    public R<TbOrder> getOrder(@PathVariable("id") Long id){
        TbOrder tbOrder = iTbOrderService.getById(id);
        return R.success(tbOrder);
    }

    @GetMapping ("/t1")
    public R<String> getnacos(){
        return R.success(nacos);
    }
    @GetMapping ("/t2")
    public R<String> getnacos1(){
        return R.success(nacosConfig.getDateformat());
    }

    @GetMapping ("/t3")
    public R<NacosConfig> getnacos3(){
        return R.success(nacosConfig);
    }


}

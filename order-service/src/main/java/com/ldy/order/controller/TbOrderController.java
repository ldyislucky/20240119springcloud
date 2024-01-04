package com.ldy.order.controller;


import com.ldy.feign.client.UserClients;
import com.ldy.feign.entity.TbUser;
import com.ldy.order.config.NacosConfig;
import com.ldy.order.entity.DTO.TbOrderDTO;
import com.ldy.order.entity.TbOrder;
import com.ldy.order.general.R;
import com.ldy.order.service.ITbOrderService;
import com.xiaoleilu.hutool.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    //@Value("${nacos.pattern.dateformat}")  //与@RefreshScope配合使用
    private String nacos;

    /**
     * 通过@ConfigurationProperties(prefix = "nacos.pattern")的方式热更新，推荐的方式
     */
    private final NacosConfig nacosConfig;
    private final ITbOrderService iTbOrderService;
    private final UserClients userClients;
    @GetMapping("/{id}")
    public R<TbOrder> getOrder(@PathVariable("id") Long id){
        TbOrder tbOrder = iTbOrderService.getById(id);
        return R.success(tbOrder);
    }

    @GetMapping("/feign/{id}")
    public R<TbOrderDTO> getOrderDto(@PathVariable("id") Long id,
                                     @RequestHeader(value = "li",required = false) String li){
        System.out.println(li);
        TbOrder tbOrder = iTbOrderService.getById(id);
        TbOrderDTO tbOrderDTO = new TbOrderDTO();
        BeanUtil.copyProperties(tbOrder,tbOrderDTO);
        TbUser tbUser = userClients.getById(tbOrder.getUserId());
        tbOrderDTO.setTbUser(tbUser);
        return R.success(tbOrderDTO);
    }

    @GetMapping("/data1/{id}")
    public R<TbOrderDTO> getOrderDto1(@PathVariable("id") Long id,
                                     @RequestHeader(value = "li",required = false) String li){
        System.out.println(li);
        TbOrder tbOrder = iTbOrderService.getById(id);
        TbOrderDTO tbOrderDTO = new TbOrderDTO();
        BeanUtil.copyProperties(tbOrder,tbOrderDTO);
        TbUser tbUser = userClients.getById(tbOrder.getUserId());
        tbOrderDTO.setTbUser(tbUser);
        return R.success(tbOrderDTO);
    }
    @GetMapping("/data2/{id}")
    public R<TbOrderDTO> getOrderDto2(@PathVariable("id") Long id,
                                      @RequestHeader(value = "li",required = false) String li){
        System.out.println(li);
        TbOrder tbOrder = iTbOrderService.getById(id);
        TbOrderDTO tbOrderDTO = new TbOrderDTO();
        BeanUtil.copyProperties(tbOrder,tbOrderDTO);
        TbUser tbUser = userClients.getById(tbOrder.getUserId());
        tbOrderDTO.setTbUser(tbUser);
        return R.success(tbOrderDTO);
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

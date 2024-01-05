package com.ldy.user.controller;


import com.ldy.user.entity.TbUser;
import com.ldy.user.general.R;
import com.ldy.user.service.ITbUserService;
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
@RequestMapping("/tb-user")
@RequiredArgsConstructor
public class TbUserController {
    private final ITbUserService iTbUserService;
    @GetMapping(("/{id}"))
    public R<TbUser> getUser(@PathVariable("id") Long id){
        TbUser tbUser = iTbUserService.getById(id);
        return R.success(tbUser);
    }
    @GetMapping("user/{id}")
    public TbUser getUserById(@PathVariable("id") Long id) throws InterruptedException {
        if (id==1){
            Thread.sleep(60);
        }
        return iTbUserService.getById(id);
    }

}

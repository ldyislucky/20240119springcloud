package org.dubbouser.ldy.controller;



import lombok.RequiredArgsConstructor;
import org.dubboapi.ldy.user.DubboUserApi;
import org.dubbopojo.ldy.R;
import org.dubbopojo.ldy.TbUser;
import org.dubbouser.ldy.service.ITbUserService;
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
 * @since 2023-12-10
 */
@RestController
@RequestMapping("/tb-user")
@RequiredArgsConstructor
public class TbUserController {
    @Autowired
    private DubboUserApi dubboUserApi;
    @GetMapping(("/{id}"))
    public R<TbUser> getUser(@PathVariable("id") Long id){
        return dubboUserApi.findUserById(id);
    }

}

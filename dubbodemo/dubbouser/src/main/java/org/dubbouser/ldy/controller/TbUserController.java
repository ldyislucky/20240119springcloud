package org.dubbouser.ldy.controller;



import lombok.RequiredArgsConstructor;
import org.dubbopojo.ldy.R;
import org.dubbopojo.ldy.TbUser;
import org.dubbouser.ldy.service.ITbUserService;
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
        return iTbUserService.findUserById(id);
    }

}

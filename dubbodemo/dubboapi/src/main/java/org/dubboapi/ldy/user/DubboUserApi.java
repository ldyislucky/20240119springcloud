package org.dubboapi.ldy.user;

import org.dubbopojo.ldy.R;
import org.dubbopojo.ldy.TbUser;

public interface DubboUserApi {
    R<TbUser> findUserById(long id);
}

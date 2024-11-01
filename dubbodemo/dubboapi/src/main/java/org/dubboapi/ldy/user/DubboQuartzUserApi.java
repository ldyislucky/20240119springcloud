package org.dubboapi.ldy.user;

import org.dubbopojo.ldy.QuartzUser;

import java.util.List;

public interface DubboQuartzUserApi {
    void addQuartzUser(List<QuartzUser> list);
}

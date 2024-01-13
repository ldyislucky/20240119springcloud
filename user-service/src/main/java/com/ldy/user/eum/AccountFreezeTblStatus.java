package com.ldy.user.eum;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum AccountFreezeTblStatus {
    //事务状态，0:try，1:confirm，2:cancel
    TRY(0,"执行"),
    CONFIRN(1,"提交"),
    CANCEL(2,"取消");
    @EnumValue
    private final int value;
    private final String desc;

    AccountFreezeTblStatus(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}

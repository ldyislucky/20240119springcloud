package com.ldy.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.ldy.user.eum.AccountFreezeTblStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author author
 * @since 2024-01-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("account_freeze_tbl")
@ApiModel(value="AccountFreezeTbl对象", description="")
public class AccountFreezeTbl implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "xid", type = IdType.AUTO)
    private String xid;

    private String userId;

    private Integer freezeMoney;

    @ApiModelProperty(value = "事务状态，0:try，1:confirm，2:cancel")
    private Integer state;

    public static abstract class State {
        public final static int TRY = 0;
        public final static int CONFIRM = 1;
        public final static int CANCEL = 2;
    }


}

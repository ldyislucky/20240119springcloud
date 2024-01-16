package com.ldy.user.mapper;

import com.ldy.user.entity.AccountTbl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author author
 * @since 2024-01-10
 */
public interface AccountTblMapper extends BaseMapper<AccountTbl> {
    @Update("update account_tbl set money = money - #{money} where user_id = #{userId}")
    int deduct(@Param("userId") String userId, @Param("money") int money);

    @Update("update account_tbl set money = money + #{money} where user_id = #{userId}")
    int undoDeduct(@Param("userId") String userId, @Param("money") int money);

}

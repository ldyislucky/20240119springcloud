package com.ldy.mapper;

import com.ldy.entity.Employee;
import com.ldy.entity.EmployeeTemp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lidongyang
 * @since 2025-02-27
 */
public interface EmployeeTempMapper extends BaseMapper<EmployeeTemp> {

    /**
     * 保存临时表
     */
    int saveTemp(EmployeeTemp EmployeeTemp);


    // 清空 employee_temp 表（TRUNCATE）
    @Delete("TRUNCATE TABLE employee_temp")
    void truncateTemp();
}

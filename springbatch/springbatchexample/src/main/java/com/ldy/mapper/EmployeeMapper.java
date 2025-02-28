package com.ldy.mapper;

import com.ldy.entity.Employee;
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
public interface EmployeeMapper extends BaseMapper<Employee> {
    /**
     * 保存临时表
     */
    int saveTemp(Employee employee);

    // 清空 employee 表（TRUNCATE）
    @Delete("TRUNCATE TABLE employee")
    void truncateAll();


}

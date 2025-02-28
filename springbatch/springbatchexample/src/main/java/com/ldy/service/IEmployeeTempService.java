package com.ldy.service;

import com.ldy.entity.EmployeeTemp;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lidongyang
 * @since 2025-02-27
 */
public interface IEmployeeTempService extends IService<EmployeeTemp> {
    /**
     * 清空employee_temp数据
     */
    void truncateTemp();
}

package com.ldy.service;

import com.ldy.entity.Employee;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.IOException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lidongyang
 * @since 2025-02-27
 */
public interface IEmployeeService extends IService<Employee> {

    /**
     * 初始化数据：生成50w数据
     */
    void dataInit() throws IOException;
    /**
     * 清空数据
     */
    void truncateAll();

    /**
     * 清空employee_temp数据
     */
    void truncateTemp();
}

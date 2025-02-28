package com.ldy.service.impl;

import com.ldy.entity.EmployeeTemp;
import com.ldy.mapper.EmployeeTempMapper;
import com.ldy.service.IEmployeeTempService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lidongyang
 * @since 2025-02-27
 */
@Service
public class EmployeeTempServiceImpl extends ServiceImpl<EmployeeTempMapper, EmployeeTemp> implements IEmployeeTempService {
    @Autowired
    EmployeeTempMapper employeeTempMapper;

    @Override
    public void truncateTemp() {
        employeeTempMapper.truncateTemp();
    }
}

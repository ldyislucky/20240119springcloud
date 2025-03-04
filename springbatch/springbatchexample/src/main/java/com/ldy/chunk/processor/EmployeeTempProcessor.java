package com.ldy.chunk.processor;

import com.ldy.entity.Employee;
import com.ldy.entity.EmployeeTemp;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class EmployeeTempProcessor implements ItemProcessor<EmployeeTemp, Employee> {
    @Override
    public Employee process(EmployeeTemp employeeTemp) throws Exception {
        Employee employee = new Employee();
        employee.setId(employeeTemp.getId());
        employee.setName(employeeTemp.getName());
        employee.setAge(employeeTemp.getAge());
        employee.setSex(employeeTemp.getSex());
        return employee;
    }
}

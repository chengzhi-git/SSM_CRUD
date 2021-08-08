package com.chengzhi.service;

import com.chengzhi.bean.Employee;
import com.chengzhi.dao.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:chengzhi
 * @Date:2021/8/8 15:28
 * @Description
 */

@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;
    public List<Employee> getAll(){
        List<Employee> employees = employeeMapper.selectByExampleWithDept(null);
        return employees;
    }
}

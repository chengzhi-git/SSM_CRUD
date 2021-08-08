package com.chengzhi.controller;

import com.chengzhi.bean.Employee;
import com.chengzhi.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author:chengzhi
 * @Date:2021/8/8 15:25
 * @Description 处理员工CRUD请求
 */

@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    /**
     * 查询员工数据（分页查询）
     * @return
     */
    @RequestMapping("/emps")
    public String getEmps(@RequestParam(value = "pn",defaultValue = "1") Integer pn, Model model) {
        //引入pageHelper分页插件
        //只需在查询之前调用
        PageHelper.startPage(pn,7);
        //startpage紧跟的查询就是分页查询
        List<Employee> emps = employeeService.getAll();
        //使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了
        //封装了详细的分页信息，包括查询出来的数据,传入连续显示的页数
        PageInfo pageInfo = new PageInfo(emps,7);
        model.addAttribute("pageInfo",pageInfo);

        return "list";
    }
}

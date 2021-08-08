package com.chengzhi.test;

import com.chengzhi.bean.Employee;
import com.chengzhi.dao.DepartmentMapper;
import com.chengzhi.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

/**
 * @Author:chengzhi
 * @Date:2021/8/8 12:02
 * @Description 测试Dao层
 * 1.导入springTest依赖
 * 2.@ContextConfiguration指定spring配置文件的位置
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {
    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    SqlSession sqlSession;
    @Test
    public void testCRUD() {
        //原生获取bean方式
//        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("application.xml");
//        DepartmentMapper bean = ioc.getBean(DepartmentMapper.class);
        //spring的单元测试方式获取bean
        System.out.println(departmentMapper);


//        departmentMapper.insertSelective(new Department(null,"开发部"));
//        departmentMapper.insertSelective(new Department(null,"测试部"));

        //employeeMapper.insertSelective(new Employee(null,"Jerry","M","Jerry@qq.com",1));

        //批量插入多个员工，可以用批量方法，也可以执行批量操作的sqlSession
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            String uid = UUID.randomUUID().toString().substring(0, 5) + i;
            mapper.insertSelective(new Employee(null,uid,"M",uid + "@qq.com",1));
        }
        long end = System.currentTimeMillis();
        System.out.println("Batch插入1000条数据耗时：" + (end - start));

        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            String uid = UUID.randomUUID().toString().substring(0, 5) + i;
            employeeMapper.insertSelective(new Employee(null,uid,"M",uid + "@qq.com",1));
        }
        long end1 = System.currentTimeMillis();
        System.out.println("循环插入1000条数据耗时：" + (end1 - start1));
    }
}

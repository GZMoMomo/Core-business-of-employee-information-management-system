package crud.test;

import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import crud.bean.Department;
import crud.bean.Employee;
import crud.dao.DepartmentMapper;
import crud.dao.EmployeeMapper;

/**
   *  ����dao�㹤��
 * @author Administrator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"} )
public class MapperTest {
	@Autowired
	DepartmentMapper departmentMapper;
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	@Autowired
	SqlSession sqlsession;
    /**
     *   ����departmentMapper
     */
	@Test
	public void testCRUD() {
		/*
		 * ApplicationContext ioc=new ClassPathXmlApplicationContext("applicationContext.xml");
		 * ioc.getBean(DepartmentMapper.class);
		 */
		System.out.println(departmentMapper);
		
		//����
		//departmentMapper.insertSelective(new Department(null,"������"));
		//departmentMapper.insertSelective(new Department(null,"���Բ�"));
	    
		//����Ա������
		//employeeMapper.insertSelective(new Employee(null,"Jerry","M","Jerry@crud.com",1));
	    
	    EmployeeMapper mapper=sqlsession.getMapper(EmployeeMapper.class);
	    for(int i=0;i<1000;i++) {
	    	String uid=UUID.randomUUID().toString().substring(0, 5)+i;
	    	mapper.insertSelective(new Employee(null,uid, "M", uid+"@crud.com",1 ));
	    }
	}

}

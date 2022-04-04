package crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import crud.bean.Employee;
import crud.bean.EmployeeExample;
import crud.bean.EmployeeExample.Criteria;
import crud.dao.EmployeeMapper;

@Service
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class) 
public class EmployeeService {
	@Autowired
	EmployeeMapper employeeMapper;

	/**
	 * 查询所有员工
	 * 
	 * @return
	 */
	
	public List<Employee> getAll() {
		// TODO Auto-generated method stub

		return employeeMapper.selectByExampleWithDept(null);
	}

	/**
	 * 保存员工
	 * 
	 * @param employee
	 */
	@CacheEvict(value= {"getAll","getEmp"},allEntries=true)
	public void saveEmp(Employee employee) {
		employeeMapper.insertSelective(employee);
	}

	/**
	 * 检验用户名是否可用
	 * 
	 * @param empName
	 * @return true:可用
	 */
	public boolean checkUser(String empName) {
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		criteria.andEmpNameEqualTo(empName);
		long count = employeeMapper.countByExample(example);
		return count == 0;
	}

	/**
	 * 按照员工ID查询员工
	 * 
	 * @param id
	 * @return
	 */
	@Cacheable("getEmp")
	public Employee getEmp(Integer id) {
		Employee employee = employeeMapper.selectByPrimaryKey(id);
		return employee;
	}

	/**
	 * 员工更新
	 * 
	 * @param employee
	 */
	@CacheEvict(value= {"getAll","getEmp"},allEntries=true)
	public void updateEmp(Employee employee) {
		employeeMapper.updateByPrimaryKeySelective(employee);
	}
      
	/**
	 * 员工删除
	 * @param id
	 */
	@CacheEvict(value= {"getAll","getEmp"},allEntries=true)
	public void deleteEmp(Integer id) {
		// TODO Auto-generated method stub
		employeeMapper.deleteByPrimaryKey(id);
	}
    
	/**
	 * 批量删除
	 * @param ids
	 */
	@CacheEvict(value= {"getAll","getEmp"},allEntries=true)
	public void deleteBath(List<Integer> ids) {
		// TODO Auto-generated method stub
		EmployeeExample example=new EmployeeExample();
		Criteria criteria = example.createCriteria();
		criteria.andEmpIdIn(ids);
		employeeMapper.deleteByExample(example);
	}

}

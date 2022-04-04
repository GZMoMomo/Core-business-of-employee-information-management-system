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
	 * ��ѯ����Ա��
	 * 
	 * @return
	 */
	
	public List<Employee> getAll() {
		// TODO Auto-generated method stub

		return employeeMapper.selectByExampleWithDept(null);
	}

	/**
	 * ����Ա��
	 * 
	 * @param employee
	 */
	@CacheEvict(value= {"getAll","getEmp"},allEntries=true)
	public void saveEmp(Employee employee) {
		employeeMapper.insertSelective(employee);
	}

	/**
	 * �����û����Ƿ����
	 * 
	 * @param empName
	 * @return true:����
	 */
	public boolean checkUser(String empName) {
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		criteria.andEmpNameEqualTo(empName);
		long count = employeeMapper.countByExample(example);
		return count == 0;
	}

	/**
	 * ����Ա��ID��ѯԱ��
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
	 * Ա������
	 * 
	 * @param employee
	 */
	@CacheEvict(value= {"getAll","getEmp"},allEntries=true)
	public void updateEmp(Employee employee) {
		employeeMapper.updateByPrimaryKeySelective(employee);
	}
      
	/**
	 * Ա��ɾ��
	 * @param id
	 */
	@CacheEvict(value= {"getAll","getEmp"},allEntries=true)
	public void deleteEmp(Integer id) {
		// TODO Auto-generated method stub
		employeeMapper.deleteByPrimaryKey(id);
	}
    
	/**
	 * ����ɾ��
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

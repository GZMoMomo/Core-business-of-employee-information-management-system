package crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import crud.bean.Department;
import crud.dao.DepartmentMapper;
@Service
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class) 
public class DepartmentService {
    @Autowired
	private DepartmentMapper departmentMapper;
	
    @Cacheable("getDepts")
	public List<Department> getDepts() {
		List<Department> list=departmentMapper.selectByExample(null);
		return list;
	}

}

package crud.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crud.bean.Role;
import crud.dao.RoleMapper;

@Service
public class RoleService {
  @Autowired
  RoleMapper roleMapper;
  
  public Set<String> listRoles(String userName){
	  List<Role> roles=roleMapper.listRolesByUserName(userName);
	  Set<String> result=new HashSet<String>();
	  for(Role role:roles) {
		  result.add(role.getName());
	  }
	  return result;
  }
}

package crud.dao;

import java.util.List;

import crud.bean.Role;

public interface RoleMapper {
	 public List<Role> listRolesByUserName(String userName);
}

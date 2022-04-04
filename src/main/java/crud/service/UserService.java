package crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crud.bean.User;
import crud.dao.UserMapper;
@Service
public class UserService {
	@Autowired
   UserMapper userMapper;
   
	public String getPassword(String name) {
		User u=userMapper.getByName(name);
		if(null==u) {
			return null;
		}
		return u.getPassword();
	}
}

package crud.dao;

import crud.bean.User;

public interface UserMapper {
   public User getByName(String name);
}

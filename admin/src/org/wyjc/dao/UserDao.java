package org.wyjc.dao;

import org.wyjc.entity.UserDTO;

public interface UserDao {
public UserDTO getUserInfo(UserDTO dto);
public void addUser(UserDTO dto);
public UserDTO getUserInfoByName(UserDTO dto);

}

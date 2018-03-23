package org.wyjc.service;

import org.wyjc.entity.UserDTO;


public interface UserService {
	public UserDTO getUserInfo(UserDTO dto);
	public void addUser(UserDTO dto);
	public UserDTO getUserInfoByName(UserDTO dto) ;
}

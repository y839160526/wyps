package org.wyjc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wyjc.dao.UserDao;
import org.wyjc.entity.UserDTO;
import org.wyjc.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public UserDTO getUserInfo(UserDTO dto) {
		return userDao.getUserInfo(dto);
	}

	public void addUser(UserDTO dto) {
		userDao.addUser(dto);	
	}

	@Override
	public UserDTO getUserInfoByName(UserDTO dto) {
		return userDao.getUserInfoByName(dto);
	}
	

}

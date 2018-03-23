package org.wyjc.dao.impl;

import java.sql.SQLException;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.wyjc.dao.UserDao;
import org.wyjc.entity.UserDTO;

public class UserDaoImpl extends SqlMapClientTemplate implements UserDao {
	private SqlMapClientTemplate sqlMapClientTemplate;

	public SqlMapClientTemplate getSqlMapClientTemplate() {
		return sqlMapClientTemplate;
	}

	public void setSqlMapClientTemplate(SqlMapClientTemplate sqlMapClientTemplate) {
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}

	public UserDTO getUserInfo(UserDTO dto) {
		UserDTO user = new UserDTO();
		try {
			user = (UserDTO) this.getSqlMapClient().queryForObject("getUserInfo", dto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public void addUser(UserDTO dto) {
		try {
			this.getSqlMapClient().insert("insertUserInfo", dto);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public UserDTO getUserInfoByName(UserDTO dto) {
		UserDTO user = new UserDTO();
		try {
			user = (UserDTO) this.getSqlMapClient().queryForObject("getUserInfoByName", dto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

}

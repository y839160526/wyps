package org.wyjc.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.wyjc.dao.CustomerDao;
import org.wyjc.entity.CustomerDTO;
import org.wyjc.util.Pager;

public class CustomerDaoImpl extends SqlMapClientTemplate implements
		CustomerDao {
	private SqlMapClientTemplate sqlMapClientTemplate;

	public SqlMapClientTemplate getSqlMapClientTemplate() {
		return sqlMapClientTemplate;
	}

	public void setSqlMapClientTemplate(
			SqlMapClientTemplate sqlMapClientTemplate) {
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}

	@SuppressWarnings("unchecked")
	public List<CustomerDTO> list() {
		List<CustomerDTO> list = null;
		try {
			list = this.getSqlMapClient().queryForList("findAllUserInfo");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public void insert(CustomerDTO userInfoDTO) {
		try {
			this.getSqlMapClient().insert("insertCustomer", userInfoDTO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(CustomerDTO userInfoDTO) {
		try {
			this.getSqlMapClient().insert("deleteCustomer", userInfoDTO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public CustomerDTO get(String id) {
		CustomerDTO userInfoDTO = null;
		userInfoDTO = (CustomerDTO) this.sqlMapClientTemplate.queryForObject(
				"getCustomerById", id);
		return userInfoDTO;
	}

	@SuppressWarnings("unchecked")
	public int getTotalCount() {
		@SuppressWarnings("unused")
		int count = 0;
		List<CustomerDTO> list = null;
		try {
			list = this.getSqlMapClient().queryForList("findAllCustomer");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list.size();
	}

	@SuppressWarnings("unchecked")
	public List<CustomerDTO> listByPager(Pager pager) {
		List<CustomerDTO> list = null;
		try {
			list = this.getSqlMapClient().queryForList(
					"findAllCustomerByPager", pager);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public void updateCustomerById(CustomerDTO customer) {

		this.sqlMapClientTemplate.update("updateCustomerById", customer);

	}

}

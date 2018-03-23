package org.wyjc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wyjc.dao.CustomerDao;
import org.wyjc.entity.CustomerDTO;
import org.wyjc.service.CustomerService;
import org.wyjc.util.Pager;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Resource
	private CustomerDao customerDao;

	public CustomerDao getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public List<CustomerDTO> list() {
		return this.customerDao.list();
	}

	public void insert(CustomerDTO userInfoDTO) {
		this.customerDao.insert(userInfoDTO);

	}

	public void delete(CustomerDTO userInfoDTO) {
		this.customerDao.delete(userInfoDTO);
	}

	public CustomerDTO get(String id) {
		// TODO Auto-generated method stub
		return this.customerDao.get(id);
	}

	public Pager listByPager(Pager pager) {
		Pager p = pager;
		p.setList(this.customerDao.listByPager(pager));
		p.setTotalCount(this.customerDao.getTotalCount());
		return p;
	}

	public void updateCustomerById(CustomerDTO customer) {
		customerDao.updateCustomerById(customer);
	}

}

package org.wyjc.service;

import java.util.List;

import org.wyjc.entity.CustomerDTO;
import org.wyjc.util.Pager;

public interface CustomerService {
	public List<CustomerDTO> list();
	public void insert(CustomerDTO userInfoDTO);
	public void delete(CustomerDTO userInfoDTO);
	public void updateCustomerById(CustomerDTO customer);
	public CustomerDTO get(String id);
	public Pager listByPager(Pager pager);

}

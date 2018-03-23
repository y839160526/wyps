package org.wyjc.dao;

import java.util.List;

import org.wyjc.entity.CustomerDTO;
import org.wyjc.util.Pager;

public interface CustomerDao {
public List<CustomerDTO> list();
public void insert(CustomerDTO userInfoDTO);
public void delete(CustomerDTO userInfoDTO);
public void updateCustomerById(CustomerDTO customer);
public CustomerDTO get(String id);
public int getTotalCount();
public List<CustomerDTO> listByPager(Pager pager);


}

package org.wyjc.action;

import java.util.List;

import javax.annotation.Resource;

import org.wyjc.entity.CustomerDTO;
import org.wyjc.service.CustomerService;
import org.wyjc.util.JsonUtil;
import org.wyjc.util.Pager;
import org.wyjc.util.UserUtil;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 业主管理
 * 
 * @author chenying
 * 
 */
public class CustomerAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	@Resource
	private CustomerService customerService;
	private List<CustomerDTO> customerList;
	private String[] ids;
	private String id;
	private Pager pager = new Pager();
	private int pageNum = 1;
	private String resultStr;
	private CustomerDTO customer;

	public CustomerDTO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
	}

	public String getResultStr() {
		return resultStr;
	}

	public void setResultStr(String resultStr) {
		this.resultStr = resultStr;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public List<CustomerDTO> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<CustomerDTO> customerList) {
		this.customerList = customerList;
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public String list() {
		//================是否登录(权限判断)开始===================
		if (!UserUtil.isLogin()) {
			return "login";
		}
		//================是否登录(权限判断)结束===================
		pager.setPageNum(pageNum);
		pager.setCurrentPage((pageNum - 1) * pager.getNumPerPage());
		pager = customerService.listByPager(pager);
		return "listCustomer";
	}

	public String delete() {
		//================是否登录(权限判断)开始===================
		if (!UserUtil.isLogin()) {
			return "login";
		}
		//================是否登录(权限判断)结束===================
		for (int i = 0; i < ids.length; i++) {
			CustomerDTO customer = new CustomerDTO();
			customer.setId(ids[i]);
			customerService.delete(customer);
		}
		this.resultStr = JsonUtil.returnAjax("200", "删除业主成功", "customerList",
				"forward", "customerAction!list.action");
		return "json";
	}

	public String deleteCustomer() {
		//================是否登录(权限判断)开始===================
		if (!UserUtil.isLogin()) {
			return "login";
		}
		//================是否登录(权限判断)结束===================
		CustomerDTO customer = new CustomerDTO();
		customer.setId(id);
		customerService.delete(customer);

		this.resultStr = JsonUtil.returnAjax("200", "删除业主成功", "customerList",
				"forward", "customerAction!list.action");
		return "json";
	}

	public String create() {
		//================是否登录(权限判断)开始===================
		if (!UserUtil.isLogin()) {
			return "login";
		}
		//================是否登录(权限判断)结束===================
		return "createCustomer";
	}

	public String save() {
		//================是否登录(权限判断)开始===================
		if (!UserUtil.isLogin()) {
			return "login";
		}
		//================是否登录(权限判断)结束===================
		customerService.insert(customer);
		this.resultStr = JsonUtil.returnAjax("200", "添加业主成功", "customerList",
				"closeCurrent", "customerAction!list.action");
		return "json";
	}

	public String update() {
		//================是否登录(权限判断)开始===================
		if (!UserUtil.isLogin()) {
			return "login";
		}
		//================是否登录(权限判断)结束===================
		customerService.updateCustomerById(customer);
		this.resultStr = JsonUtil.returnAjax("200", "修改业主成功", "customerList",
				"closeCurrent", "customerAction!list.action");
		return "json";
	}

	public String edit() {
		//================是否登录(权限判断)开始===================
		if (!UserUtil.isLogin()) {
			return "login";
		}
		//================是否登录(权限判断)结束===================
		customer = customerService.get(id);
		return "editCustomer";
	}
	public String exportExcel(){
		//================是否登录(权限判断)开始===================
		if (!UserUtil.isLogin()) {
			return "login";
		}
		//================是否登录(权限判断)结束===================
		pager = customerService.listByPager(pager);
		return "exportExcel";
		
	}

}

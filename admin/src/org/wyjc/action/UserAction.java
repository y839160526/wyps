package org.wyjc.action;

import java.util.Map;

import javax.annotation.Resource;

import org.wyjc.entity.UserDTO;
import org.wyjc.service.UserService;
import org.wyjc.util.JsonUtil;
import org.wyjc.util.UserUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 用户管理
 * 
 * @author chenying
 * 
 */
public class UserAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	@Resource
	private UserService userService;
	private UserDTO user;
	private String resultStr;

	public String getResultStr() {
		return resultStr;
	}

	public void setResultStr(String resultStr) {
		this.resultStr = resultStr;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String valiLogin() {
		UserDTO userInfo = userService.getUserInfo(user);
		if (userInfo != null) {
			ActionContext actionContext = ActionContext.getContext();
			Map session = actionContext.getSession();
			session.put("userInfo", userInfo);
			this.resultStr = JsonUtil.returnAjax("200", "success", "", String.valueOf(userInfo.getId()), "");
			return "json";
		} else {
			this.resultStr = JsonUtil.returnAjax("200", "faile", "", "", "");
			return "json";
		}
	}
	public String valiName() {
		UserDTO userInfo = userService.getUserInfoByName(user);
		if (userInfo != null) {
			this.resultStr = JsonUtil.returnAjax("200", "faile", "", String.valueOf(userInfo.getId()), "");
			return "json";
		} else {
			this.resultStr = JsonUtil.returnAjax("200", "success", "", "", "");
			return "json";
		}
	}

	public String register() {
		userService.addUser(user);
		this.resultStr = JsonUtil.returnAjax("200", "success", "", "", "");
		return "json";

	}

	public String logout() {
		ActionContext actionContext = ActionContext.getContext();
		actionContext.getSession().clear();
		return "login";
	}

	public String index() {
		// ================是否登录(权限判断)开始===================
		if (!UserUtil.isLogin()) {
			return "login";
		}
		// ================是否登录(权限判断)结束===================
		return "index";
	}

	public String login() {

		return "login";
	}
}

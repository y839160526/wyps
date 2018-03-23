package org.wyjc.util;

import java.util.Map;

import org.wyjc.entity.UserDTO;

import com.opensymphony.xwork2.ActionContext;

public class UserUtil {
	public static boolean isLogin(){
		ActionContext actionContext = ActionContext.getContext();
		Map session = actionContext.getSession();
		UserDTO userInfo=(UserDTO) session.get("userInfo");
		if(userInfo==null){
			return false;
		}
		return true;
	}
	public static UserDTO getUserInfo(){
		ActionContext actionContext = ActionContext.getContext();
		Map session = actionContext.getSession();
		UserDTO userInfo=(UserDTO) session.get("userInfo");
		
		return userInfo;
	}

}

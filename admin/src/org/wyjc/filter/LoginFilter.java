package org.wyjc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.wyjc.entity.UserDTO;

/**
 * 登录过滤器
 * 
 * @author chenying
 * 
 */
public class LoginFilter implements Filter {
	public void init(FilterConfig config) {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String url = req.getRequestURI();
		if (url.equals("/login.html")) {
		} else {
			// 如果会话不存在则返回null
			HttpSession session = req.getSession(true);

			UserDTO userInfo = (UserDTO) session.getAttribute("userInfo");

			// 可以在数据库中提取数据，进行比对。
			if (userInfo == null) {
				resp.sendRedirect("login.html");
				return;
			}
		}
		chain.doFilter(request, response);
	}

	public void destroy() {

	}
}
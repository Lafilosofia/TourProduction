package com.lhz.tourproduct.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lhz.tourproduct.project.commons.ControllerException;
import com.lhz.tourproduct.user.entity.User;

public class CommonsFilter implements Filter {

	public CommonsFilter() {
		
	}

	public void destroy() {
		

	}

	public void doFilter(ServletRequest request, 
						  ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//类型转换
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse res=(HttpServletResponse)response;
		String path=req.getRequestURI();
		//System.out.println("filterPath="+path);
		if(path.endsWith("login.do")
				||path.endsWith("register.do")
				
				||path.endsWith("toUserRegister.do")
				
				||path.endsWith("toUserLogin.do")
				){
			//清缓存
			//res.addHeader("cache-Control", "no-cache");
			chain.doFilter(request, response);
			return;
		}
													   
		User user=(User)req.getSession().getAttribute("userLogin");
		//System.out.println("user="+user);
		if(user==null){
			//如果user是空就重定向到登录页面(采用绝对路径)
			String url=req.getContextPath()+"/login.do";
			res.sendRedirect(url);
			return;
		}
		chain.doFilter(request, response);

	}

	public void init(FilterConfig arg0) throws ServletException {
		

	}

}

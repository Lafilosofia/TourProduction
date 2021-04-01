package com.lhz.tourproduct.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.lhz.tourproduct.user.entity.User;
@Component
public class CommonsInterceptor implements HandlerInterceptor {

	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	
		//System.out.println("aterCompletion");
	}

	public void postHandle(HttpServletRequest request, 
							HttpServletResponse response, 
							Object arg2, ModelAndView arg3)
			throws Exception {
		
		//System.out.println("postHandle");
	}

	public boolean preHandle(HttpServletRequest request,
							  HttpServletResponse response, 
							  Object arg2) throws Exception {
		//System.out.println("preHandler");
		User user=(User)request.getSession().getAttribute("userLogin");
		//System.out.println("user:"+user);
		if(user==null){
			//System.out.println("ÇëµÇÂ¼!");
//			String json="{\"state\":1,\"message\":\"ÇëµÇÂ¼ÔÚ·ÃÎÊ!\"}";
//			response.setContentType("text/html;charset=utf-8");
//			response.getWriter().print(json);
			String url=request.getContextPath()+"/error.html";
			response.sendRedirect(url);
			//System.out.println("²âÊÔµÇÂ¼");
			return false;
		}
		return true;
	}

}

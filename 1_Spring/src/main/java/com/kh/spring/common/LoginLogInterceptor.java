package com.kh.spring.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import com.kh.spring.member.model.vo.Member;

public class LoginLogInterceptor implements HandlerInterceptor{

	private Logger logger= LoggerFactory.getLogger(LoginLogInterceptor.class);
	
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		HttpSession session =request.getSession();
		Member loginUser = (Member)session.getAttribute("loginUser");
		
		if(loginUser!=null) {
			logger.info(loginUser.getId());
		}
	}	
}

package com.kh.spring.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.kh.spring.member.model.vo.Member;

public class CheckLoginInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session =request.getSession();
		Member loginUser = (Member)session.getAttribute("loginUser");
		
		if(loginUser == null) {
			String url = request.getRequestURI(); 
			//현재 요청 받은 URI
			
			String msg = null;
			if(url.equals("selectBoard.bo") || url.equals("selectAttm.at")){
				msg="로그인 후 이용하세요";
			}else {
				msg="로그인 세션이 만료되어 로그인 화면으로 넘어갑니다.";
			}
			session.setAttribute("msg", msg);
			response.sendRedirect("loginView.me");
			return false;
	}
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}

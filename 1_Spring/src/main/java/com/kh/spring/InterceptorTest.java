package com.kh.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class InterceptorTest implements HandlerInterceptor {
	
	private Logger logger = LoggerFactory.getLogger(InterceptorTest.class);
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//DispatcherServlet이 Controller 호출 전 수행(Controller로 요청이 들어가기 전)
		logger.debug("=========START===========");
		logger.debug(request.getRequestURI());
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		//Controller에서 DispatcherServlet으로 리턴되는 순간에 수행
		
		logger.debug("----------view----------");
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// 뷰에서 모든 작업(최종 결과 생성)이 완료된 후 수행
		logger.debug("===========end=============");
		
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}

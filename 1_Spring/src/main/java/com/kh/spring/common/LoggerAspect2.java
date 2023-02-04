package com.kh.spring.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggerAspect2 {
	private Logger logger = LoggerFactory.getLogger(LoggerAspect1.class);

	/*
	 * @Pointcut("execution(* com.kh.spring..*(..))")
	 *  public void pointcutAll() {}
	 */
	@Around("execution(* com.kh.spring..*(..))")
	public Object loggerAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		Signature signature = joinPoint.getSignature();
		// Signature : 현재 AOP가 적용된 메소드 정보
		String type = signature.getDeclaringTypeName();// 실행되는 메소드가 있는 클래스 풀네임
		String methodName = signature.getName();// 타켓 객체의 메소드

		String componentName = "";
		if (type.indexOf("Controller") > -1) {
			componentName = "Controller \t:";
		} else if (type.indexOf("Service") > -1) {
			componentName = "ServiceImpl \t:";
		} else if (type.indexOf("DAO") > -1) {
			componentName = "DAO \t\t:";
		}
		logger.debug("[Before]" + componentName + type + "." + methodName + "()");
		Object obj = null;
	
			obj = joinPoint.proceed();
		

		logger.debug("[After]" + componentName + type + "." + methodName + "()");
		return obj;
	}

}

package com.kh.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log4jTest {
	private Logger logger = LoggerFactory.getLogger(Log4jTest.class);
	
	public static void main(String[] args) {
		new Log4jTest().test();
	}
	
	public void test() {
		logger.trace("trace log");
		logger.debug("debug log");
		logger.info("info log");
		logger.warn("warn log");
		logger.error("error log");
//		logger.fatal("fatal log");
//		왜 trace와 debug가 나오지 않은가?
//		현재 패키지는 com.kh.spring이다 .
// 		log4j.xml을 확인 하면 com.kh.spring설정 레벨이 info로 되어있기 때문에 info 이후 레벨이 찍히는 걸 확인 할 수 있다!
		
	}
}

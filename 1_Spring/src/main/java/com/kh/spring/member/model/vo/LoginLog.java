package com.kh.spring.member.model.vo;

import java.sql.Date;

public class LoginLog {
	private String id;
	private String startTime;
	private String dateTime;
	private String loginCheck;

	public LoginLog() {}

	public LoginLog(String id, String startTime, String dateTime, String loginCheck) {
		super();
		this.id = id;
		this.startTime = startTime;
		this.dateTime = dateTime;
		this.loginCheck = loginCheck;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getLoginCheck() {
		return loginCheck;
	}

	public void setLoginCheck(String loginCheck) {
		this.loginCheck = loginCheck;
	}

	@Override
	public String toString() {
		return "LoginLog [id=" + id + ", startTime=" + startTime + ", dateTime=" + dateTime + ", loginCheck="
				+ loginCheck + "]";
	}

	
	
	
}

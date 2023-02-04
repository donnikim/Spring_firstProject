package com.kh.spring.member.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import com.kh.spring.member.model.vo.LoginLog;
import com.kh.spring.member.model.vo.Member;

public interface MemberService {

	Member login(Member m);

	int insertMember(Member m);

	ArrayList<HashMap<String, Object>> selectMyList(String id);

	int updateMember(Member m);

	int updatePwd(HashMap<String, String> map);

	int deleteMember(String id);

	int checkId(String id);

	int checkNickName(String nickName);

	LoginLog searchLog(String id);

	int insertTime(String id);

	int updateTime(String id);

	LoginLog loginCheck(String id);

	int updateCheck(String id);







	



}

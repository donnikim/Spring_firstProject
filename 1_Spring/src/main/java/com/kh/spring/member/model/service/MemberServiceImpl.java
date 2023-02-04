package com.kh.spring.member.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.member.model.dao.MemberDAO;
import com.kh.spring.member.model.vo.LoginLog;
import com.kh.spring.member.model.vo.Member;

@Service("mService")
public class MemberServiceImpl implements MemberService {
	//의존성 주입
	//앞으로 이제 객체를 안만들고 어노테이션으로 연결을 할것이다.
	//객체는 프레임 워크가 만들도록 시킨다.
	// 컨트롤 필드 객체 위에 autowired 어노테이션을 넣어 어디서든 객체를 불러올수 있게 만든다. 즉 프레임워크가 자동적으로 만들고 서비스로 넣는다. 
	
	@Autowired
	private MemberDAO mDAO;
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public Member login(Member m) {
//		System.out.println(sqlSession);
		
		
		return mDAO.login(sqlSession,m);
	}
	@Override
	public int insertMember(Member m) {
		
		return mDAO.insertMember(sqlSession, m);
	}
	@Override
	public ArrayList<HashMap<String, Object>> selectMyList(String id) {
		ArrayList<HashMap<String, Object>> list =mDAO.selectMyList(sqlSession, id);
		return list;
	}
	@Override
	public int updateMember(Member m) {
		int result = mDAO.updateMember(sqlSession,m); 
		
		return result;
	}
	@Override
	public int updatePwd(HashMap<String, String> map) {
		int result =mDAO.updatePwd(sqlSession,map);
		
		return result;
	}
	@Override
	public int deleteMember(String id) {
		int result=mDAO.deleteMember(sqlSession,id);
		
		return result;
	}
	@Override
	public int checkId(String id) {
		return mDAO.checkId(sqlSession,id);
	}
	@Override
	public int checkNickName(String nickName) {
		return mDAO.checkNickName(sqlSession,nickName);
	}
	@Override
	public LoginLog searchLog(String id) {
		return mDAO.searchLog(sqlSession,id);
	}
	@Override
	public int insertTime(String id) {
		return mDAO.insertTime(sqlSession,id);
	}
	@Override
	public int updateTime(String id) {
		return mDAO.updateTime(sqlSession,id);
	}
	@Override
	public LoginLog loginCheck(String id) {
		return mDAO.loginCheck(sqlSession,id);
	}
	@Override
	public int updateCheck(String id) {
		return mDAO.updateCheck(sqlSession,id);
	}


}

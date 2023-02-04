package com.kh.spring.member.model.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring.member.model.vo.LoginLog;
import com.kh.spring.member.model.vo.Member;

@Repository("mDAO")
public class MemberDAO {

	public Member login(SqlSessionTemplate sqlSession, Member m) {


		
		return sqlSession.selectOne("memberMapper.login",m);
	}

	public int insertMember(SqlSessionTemplate sqlSession, Member m) {

		
		
		return sqlSession.insert("memberMapper.insertMember",m);
	}

	public ArrayList<HashMap<String, Object>>  selectMyList(SqlSessionTemplate sqlSession, String id) {

		ArrayList<HashMap<String, Object>> list =(ArrayList)sqlSession.selectList("memberMapper.selectMyList",id);
		
		return list;
	}

	public int updateMember(SqlSessionTemplate sqlSession, Member m) {
		int result = sqlSession.update("memberMapper.updateMember",m);
		
		return result;
	}

	public int updatePwd(SqlSessionTemplate sqlSession, HashMap<String, String> map) {
		int result =sqlSession.update("memberMapper.updatePwd",map);
		return result;
	}

	public int deleteMember(SqlSessionTemplate sqlSession, String id) {
		int result =sqlSession.update("memberMapper.deleteMember",id);
		
		return result;
	}

	public int checkId(SqlSessionTemplate sqlSession, String id) {
		return sqlSession.selectOne("memberMapper.checkId", id);
	}

	public int checkNickName(SqlSessionTemplate sqlSession, String nickName) {
		return sqlSession.selectOne("memberMapper.checkNickName",nickName);
	}

	public LoginLog searchLog(SqlSessionTemplate sqlSession, String id) {
		return sqlSession.selectOne("memberMapper.searchLog",id);
	}

	public int insertTime(SqlSessionTemplate sqlSession, String id) {
		return sqlSession.insert("memberMapper.insertTime",id);
	}

	public int updateTime(SqlSessionTemplate sqlSession, String id) {
		return sqlSession.update("memberMapper.updateTime",id);
	}

	public LoginLog loginCheck(SqlSessionTemplate sqlSession, String id) {
		return sqlSession.selectOne("memberMapper.loginCheck",id);
	}

	public int updateCheck(SqlSessionTemplate sqlSession, String id) {
		return sqlSession.update("memberMapper.updateCheck",id);
	}



	
}

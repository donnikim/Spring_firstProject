package com.kh.spring.board.model.service;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.spring.board.model.dao.BoardDAO;
import com.kh.spring.board.model.vo.Attachment;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.PageInfo;
import com.kh.spring.board.model.vo.Reply;

@Service("bService")
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	private BoardDAO bDAO;

	@Override
	public int getListCount(int i) {

		return bDAO.getListCount(sqlSession,i);
		
	}

	@Override
	public ArrayList<Board> selectBoardList(PageInfo pi,int i) {

	
		
		return bDAO.selectBoardList(sqlSession,pi,i);
	}

	@Override
	public int insertBoard(Board b) {

		return bDAO.insertBoard(sqlSession,b);
	}

	@Override
	@Transactional
	public Board selectBoard(int bId, boolean yn) {
		int result=0;
		
		if(yn) {
			result=bDAO.addCount(sqlSession,bId);
		}
		
		Board b =bDAO.selectBoard(sqlSession,bId);
		
		
		return b;
	}

	@Override
	public int updateBoard(Board b) {
		return bDAO.updateBoard(sqlSession,b);
	}

	@Override
	public int deleteBoard(int bId) {

		
		return bDAO.deleteBoard(sqlSession,bId);
	}

	@Override
	public int insertAttm(ArrayList<Attachment> list) {
		return bDAO.insertAttm(sqlSession,list);
	}


	@Override
	public ArrayList<Attachment> selectAttmList(Integer bId) {
		// TODO Auto-generated method stub
		return bDAO.selectAttmList(sqlSession,bId);
	}

	@Override
	public int deleteAttm(ArrayList<String> delRename) {
		return bDAO.deleteAttm(sqlSession,delRename);
	}

	@Override
	public void updateAttmLevel(int boardId) {
			bDAO.updateAttmLevel(sqlSession,boardId);
	}

	@Override
	public ArrayList<Reply> selectReply(int bId) {
		return bDAO.selectReply(sqlSession,bId);
	}

	@Override
	public int insertReply(Reply r) {
		return bDAO.insertReply(sqlSession,r);
	}

		





}

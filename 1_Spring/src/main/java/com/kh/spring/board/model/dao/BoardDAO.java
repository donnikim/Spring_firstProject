package com.kh.spring.board.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring.board.model.vo.Attachment;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.PageInfo;
import com.kh.spring.board.model.vo.Reply;

@Repository("bDAO")
public class BoardDAO {

	public int getListCount(SqlSessionTemplate sqlSession, int i) {
		
		int result=sqlSession.selectOne("boardMapper.listCount",i);
		return result;
	}

	public ArrayList<Board> selectBoardList(SqlSessionTemplate sqlSession, PageInfo pi, int i) {

		int offset=(pi.getCurrentPage()-1)*pi.getBoardLimit();
		
		RowBounds rowBounds= new RowBounds(offset,pi.getBoardLimit());
		
		ArrayList<Board> list=(ArrayList)sqlSession.selectList("boardMapper.selectBoardList", i, rowBounds);
		
		return list;
	}

	public int insertBoard(SqlSessionTemplate sqlSession, Board b) {

		
		return sqlSession.insert("boardMapper.insertBoard",b);
	}

	
	
	public int addCount(SqlSessionTemplate sqlSession, int bId) {
		return sqlSession.update("boardMapper.addCount",bId);
	}

	public Board selectBoard(SqlSessionTemplate sqlSession, int bId) {
		return sqlSession.selectOne("boardMapper.selectBoard", bId);
	}

	public int updateBoard(SqlSessionTemplate sqlSession, Board b) {
		return sqlSession.update("boardMapper.updateBoard",b);
	}

	public int deleteBoard(SqlSessionTemplate sqlSession, int bId) {
		return sqlSession.update("boardMapper.deleteBoard",bId);
	}

	public int insertAttm(SqlSessionTemplate sqlSession, ArrayList<Attachment> list) {
		return sqlSession.insert("boardMapper.insertAttm",list);
	}

	public ArrayList<Attachment> selectAttmList(SqlSessionTemplate sqlSession, Integer bId) {
		 ArrayList<Attachment> list =(ArrayList)sqlSession.selectList("boardMapper.selectAttmList",bId);
		
		return list;
	}

	public int deleteAttm(SqlSessionTemplate sqlSession, ArrayList<String> delRename) {
		return sqlSession.delete("boardMapper.deleteAttm",delRename);
	}

	public void updateAttmLevel(SqlSessionTemplate sqlSession, int boardId) {

		sqlSession.update("boardMapper.updateAttmLevel",boardId);
	}

	public ArrayList<Reply> selectReply(SqlSessionTemplate sqlSession, int bId) {

		
		return (ArrayList)sqlSession.selectList("boardMapper.selectReply",bId);
	}

	public int insertReply(SqlSessionTemplate sqlSession, Reply r) {
		return sqlSession.insert("boardMapper.insertReply",r);
	}

}

package com.kh.spring.board.model.service;

import java.util.ArrayList;

import com.kh.spring.board.model.vo.Attachment;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.PageInfo;
import com.kh.spring.board.model.vo.Reply;

public interface BoardService {

	int getListCount(int i);

	ArrayList<Board> selectBoardList(PageInfo pi, int i);

	int insertBoard(Board b);

	Board selectBoard(int bId, boolean yn);

	int updateBoard(Board b);

	int deleteBoard(int bId);

	int insertAttm(ArrayList<Attachment> list);

	ArrayList<Attachment> selectAttmList(Integer bId);

	int deleteAttm(ArrayList<String> delRename);

	void updateAttmLevel(int boardId);

	ArrayList<Reply> selectReply(int bId);

	int insertReply(Reply r);




}

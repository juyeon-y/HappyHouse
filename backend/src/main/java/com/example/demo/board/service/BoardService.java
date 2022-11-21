package com.example.demo.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.board.dto.Board;
import com.example.demo.board.mapper.BoardMapper;

@Service
public class BoardService {

	@Autowired
	BoardMapper boardMapper;

	public List<Board> getBoardList() {
		return boardMapper.getBoardList();
	}

	public Board selectOne(int code, int member_id) {
		Board board = boardMapper.selectOne(code);
		board.setLike(boardMapper.isLike(member_id, code));
		return board;
	}

	public long writeBoard(Board board) {
		long write = boardMapper.writeBoard(board);
		boardMapper.createFile(board);
		return write;
	}

	public boolean modifyBoard(String title, String content, String code) {
		return boardMapper.modifyBoard(title,content,code);
	}

	public boolean deleteBoard(int code) {
		return boardMapper.deleteBoard(code);
	}

	public int likeUp(int member_id, int board_code) {
		return boardMapper.likeUp(member_id, board_code);

	}

	public int likeDown(int member_id, int board_code) {
		return boardMapper.likeDown(member_id, board_code);

	}

}

package com.example.demo.board.service;

import java.util.List;
import java.util.Map;

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

	public Board selectOne(Map<String, Object> map) {
		Board board = boardMapper.selectOne(map);
		// board.setLike(boardMapper.isLike(member_id, code));
		return board;
	}

	public long writeBoard(Board board) {
		long write = boardMapper.writeBoard(board);
		return write;
	}

	public boolean modifyBoard(Board board) {
		return boardMapper.modifyBoard(board);
	}

	public boolean deleteBoard(int code) {
		return boardMapper.deleteBoard(code);
	}

	public int likeUp(Map<String, Object> map) {
		return boardMapper.likeUp(map);

	}

	public int likeDown(Map<String, Object> map) {
		return boardMapper.likeDown(map);

	}

}

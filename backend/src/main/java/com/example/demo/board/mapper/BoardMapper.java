package com.example.demo.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.example.demo.board.dto.Board;

@Mapper
@Repository
public interface BoardMapper {

	Board selectOne(int code) throws DataAccessException;

	List<Board> getBoardList() throws DataAccessException;

	long writeBoard(Board board);

	boolean modifyBoard(String title, String content, String code);

	boolean deleteBoard(int code);

	void createFile(Board board);

	int isLike(int member_id, int code);

	int likeUp(int member_id, int board_code);

	int likeDown(int member_id, int board_code);
}

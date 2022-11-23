package com.example.demo.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.example.demo.board.dto.Board;

@Mapper
@Repository
public interface BoardMapper {

	Board selectOne(Map<String, Object> map) throws DataAccessException;

	List<Board> getBoardList() throws DataAccessException;

	long writeBoard(Board board);

	boolean modifyBoard(Board board);

	boolean deleteBoard(int code);

	int isLike(int member_id, int code);

	int likeUp(Map<String, Object> map);

	int likeDown(Map<String, Object> map);
}

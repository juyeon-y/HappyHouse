package com.example.demo.comment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.demo.comment.dto.Comment;


@Mapper
@Repository
public interface CommentMapper {

	List<Comment> getCmtList(int board_code);

	long writeCmt(Comment comment);

}

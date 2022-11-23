package com.example.demo.comment.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.demo.comment.dto.Comment;


@Mapper
@Repository
public interface CommentMapper {

	List<Comment> getCmtList(Map<String, Object> map);

	long writeCmt(Comment comment);

	boolean deleteCmt(int id);

	boolean modifyCmt(Comment comment);

	int likeUp(Map<String, Object> map);

	int likeDown(Map<String, Object> map);

}

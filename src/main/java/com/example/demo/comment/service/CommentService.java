package com.example.demo.comment.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.comment.dto.Comment;
import com.example.demo.comment.mapper.CommentMapper;

@Service
public class CommentService {

	@Autowired
	CommentMapper commentMapper;

	public List<Comment> getCmtList(Map<String, Object> map) {
		return commentMapper.getCmtList(map);
	}

	public long writeCmt(Comment comment) {
		return commentMapper.writeCmt(comment);
	}

	public boolean deleteCmt(int id) {
		return commentMapper.deleteCmt(id);
	}

	public boolean modifyCmt(Comment comment) {
		return commentMapper.modifyCmt(comment);
	}

	public int likeUp(int id) {
		return commentMapper.likeUp(id);
	}

	public int likeDown(int id) {
		return commentMapper.likeDown(id);
	}

}

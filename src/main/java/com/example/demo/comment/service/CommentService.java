package com.example.demo.comment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.comment.dto.Comment;
import com.example.demo.comment.mapper.CommentMapper;

@Service
public class CommentService {

	@Autowired
	CommentMapper commentMapper;

	public List<Comment> getCmtList(int board_code) {
		return commentMapper.getCmtList(board_code);
	}

	public long writeCmt(Comment comment) {
		return commentMapper.writeCmt(comment);
	}

}

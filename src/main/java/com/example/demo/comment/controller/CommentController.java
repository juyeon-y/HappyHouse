package com.example.demo.comment.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.board.dto.Board;
import com.example.demo.board.dto.FileInfo;
import com.example.demo.comment.dto.Comment;
import com.example.demo.comment.service.CommentService;

@Controller
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired CommentService commentService;
	
	@GetMapping("{board_code}")
	@ResponseBody
	public List<Comment> getCmtList(@PathVariable int board_code){
		List<Comment> list=commentService.getCmtList(board_code);
		return list;
	}
	
	@PostMapping("/write")
	@ResponseBody
	public String writeCmt(Comment comment) {
		long cnt = commentService.writeCmt(comment);
		if (cnt > 0) {
			return "success";
		} else {
			return "fail";
		}
	}
}

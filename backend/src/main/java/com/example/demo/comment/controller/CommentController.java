package com.example.demo.comment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.comment.dto.Comment;
import com.example.demo.comment.service.CommentService;

@Controller
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	CommentService commentService;

	@GetMapping("{board_code}")
	@ResponseBody
	public List<Comment> getCmtList(@PathVariable int board_code,@RequestParam("member_id") int member_id) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("board_code",board_code);
		map.put("member_id",member_id);
		List<Comment> list = commentService.getCmtList(map);
		return list;
	}

	@PostMapping("{id}")
	@ResponseBody
	public String deleteCmt(@PathVariable int id) {
		if (commentService.deleteCmt(id)) {
			return "success";
		} else {
			return "fail";
		}
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

	@PostMapping("/edit")
	@ResponseBody
	public String modifyCmt(@RequestBody Comment comment) {
		if (commentService.modifyCmt(comment)) {
			return "success";
		} else {
			return "fail";
		}
	}

	@ResponseBody
	@PostMapping("/likeUp")
	public int likeup(@RequestParam("id") int id) {
		return commentService.likeUp(id);
	}

	@ResponseBody
	@PostMapping("/likeDown")
	public int likeDown(@RequestParam("id") int id) {
		return commentService.likeDown(id);
	}
}

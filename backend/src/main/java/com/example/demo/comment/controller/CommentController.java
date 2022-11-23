package com.example.demo.comment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
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

	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping("/list")
	@ResponseBody
	public List<Comment> getCmtList(@RequestParam("code") int code,@RequestParam("username") String username) {
		System.out.println("code: "+code+"username: "+username);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("code",code);
		map.put("username",username);
		List<Comment> list = commentService.getCmtList(map);
		return list;
	}

	@PostMapping("/delete")
	@ResponseBody
	public String deleteCmt(@RequestParam("id") int id) {
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
	public int likeup(@RequestParam("comment_id") int code,@RequestParam("user_id") int id) {
		System.out.println("code: "+code+"id: "+id);
		Map<String,Object> map=new HashMap<>();
		map.put("comment_id",code);
		map.put("member_id",id);
		return commentService.likeUp(map);
	}

	@ResponseBody
	@PostMapping("/likeDown")
	public int likeDown(@RequestParam("comment_id") int code,@RequestParam("user_id") int id) {
		Map<String,Object> map=new HashMap<>();
		map.put("comment_id",code);
		map.put("member_id",id);
		return commentService.likeDown(map);
	}
}

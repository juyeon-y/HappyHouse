package com.example.demo.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import com.example.demo.board.dto.Board;
import com.example.demo.board.service.BoardService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	BoardService boardService;

	@CrossOrigin(origins = "http://localhost:8080")
	@PostMapping
	@ResponseBody
	public PageInfo<Board> getBoardList(HttpServletRequest request) {
		System.out.println("pageNum" + request.getParameter("pageNum"));
		System.out.println("pageSize" + request.getParameter("pageSize"));

		PageHelper.startPage(request);
		List<Board> list = boardService.getBoardList();
		return PageInfo.of(list);
	}

	@GetMapping("/view")
	@ResponseBody
	public Board boardView(@RequestParam("code") int code,@RequestParam("username") String username) {
		System.out.println("code : "+code +"username"+username);
		Map<String,Object> map=new HashMap<>();
		map.put("code", code);
		map.put("username", username);
		Board board = boardService.selectOne(map);
		System.out.println(board.toString());
		return board;
	}

	@ResponseBody
	@PostMapping("/likeUp")
	public int likeup(@RequestParam("id") int id, @RequestParam("code") int code) {
		Map<String,Object> map=new HashMap<>();
		map.put("code",code);
		map.put("id",id);
		return boardService.likeUp(map);
	}

	@ResponseBody
	@PostMapping("/likeDown")
	public int likeDown(@RequestParam("id") int id, @RequestParam("code") int code) {
		Map<String,Object> map=new HashMap<>();
		map.put("code",code);
		map.put("id",id);
		return boardService.likeDown(map);
	}

	@PostMapping("/write")
	@ResponseBody
	public String boardWrite(@RequestBody Board board) {
		System.out.println("boardWrite: "+ board.toString());
		long cnt = boardService.writeBoard(board);
		if (cnt > 0) {
			return "success";
		} else {
			return "fail";
		}
	}

	@PostMapping("/edit")
	@ResponseBody
	public String modifyBoard(@RequestBody Board board) {
		if (boardService.modifyBoard(board)) {
			return "success";
		} else {
			return "fail";
		}
	}

	@PostMapping("{code}")
	@ResponseBody
	public String deleteBoard(@PathVariable int code) {
		if (boardService.deleteBoard(code)) {
			return "success";
		} else {
			return "fail";
		}
	}

	/*
	 * 댓글->comment 따로 패키지 파서, db erd에 맞게 다시 정리하고 게시판 글 불러올때 서브쿼리로 댓글까지 싸악 불러오도록 여튼
	 * 게시판, 댓글 해오기
	 */

}

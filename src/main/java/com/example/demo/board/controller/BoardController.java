package com.example.demo.board.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.board.dto.Board;
import com.example.demo.board.dto.FileInfo;
import com.example.demo.board.service.BoardService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	BoardService boardService;

	@PostMapping("/list")
	@ResponseBody
	public PageInfo<Board> getBoardList() {
		List<Board> list = boardService.getBoardList();
		PageHelper.startPage(1, 5);
		return PageInfo.of(list);
	}

	@GetMapping("{code}")
	@ResponseBody
	public Board boardView(@PathVariable int code, @RequestParam("member_id") int member_id) {
		Board board = boardService.selectOne(code, member_id);
		System.out.println(board);
		return board;
	}

	@ResponseBody
	@PostMapping("/likeUp")
	public int likeup(@RequestParam("member_id") int member_id, @RequestParam("board_code") int board_code) {
		return boardService.likeUp(member_id, board_code);
	}

	@ResponseBody
	@PostMapping("/likeDown")
	public int likeDown(@RequestParam("member_id") int member_id, @RequestParam("board_code") int board_code) {
		return boardService.likeDown(member_id, board_code);
	}

	@PostMapping("/write")
	@ResponseBody
	public String boardWrite(@RequestParam("file") MultipartFile[] files, Board board) {
		String folderPath = "./files";
		File folder = new File(folderPath);
		System.out.println(folder);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		System.out.println(folder.getAbsolutePath());
		List<FileInfo> fileInfos = new ArrayList<>();
		String today = new SimpleDateFormat("yyMMdd").format(new Date());
		for (MultipartFile mfile : files) { // 선택한 파일들을 가져와서 루프
			System.out.println(mfile.getOriginalFilename());
			FileInfo fileInfoDto = new FileInfo();
			String originalFileName = mfile.getOriginalFilename(); // 파일명 구해서
			assert originalFileName != null;
			if (!originalFileName.isEmpty()) {
				String saveFileName = UUID.randomUUID() + originalFileName.substring(originalFileName.lastIndexOf('.'));
				fileInfoDto.setSaveFolder(today);
				fileInfoDto.setOriginalFile(originalFileName);
				fileInfoDto.setSaveFile(saveFileName);
				try {
					File dest = new File(folder.getAbsolutePath(), saveFileName);
					mfile.transferTo(dest); // 파일을 내가 지정한 path에 저장해둔다.
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
			fileInfos.add(fileInfoDto);
		}
		System.out.println(fileInfos.toString());
		board.setFileInfos(fileInfos);

		long cnt = boardService.writeBoard(board);
		if (cnt > 0) {
			return "success";
		} else {
			return "fail";
		}
	}

	@PostMapping("/edit")
	@ResponseBody
	public String modifyBoard(@RequestParam("title") String title,@RequestParam("content") String content,@RequestParam("code") String code) {
		if (boardService.modifyBoard(title,content,code)) {
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

	@GetMapping(value = "/download")
	public ModelAndView downloadFile(@RequestParam("sfolder") String sfolder, @RequestParam("ofile") String ofile,
			@RequestParam("sfile") String sfile, HttpSession session) {
		Map<String, Object> fileInfo = new HashMap<String, Object>();
		fileInfo.put("sfolder", sfolder);
		fileInfo.put("ofile", ofile);
		fileInfo.put("sfile", sfile);

		return new ModelAndView("fileDownLoadView", "downloadFile", fileInfo);
	}

	/*
	 * 댓글->comment 따로 패키지 파서, db erd에 맞게 다시 정리하고 게시판 글 불러올때 서브쿼리로 댓글까지 싸악 불러오도록 여튼
	 * 게시판, 댓글 해오기
	 */

}

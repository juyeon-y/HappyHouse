package com.example.demo.interest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.house.model.HouseInfoDto;
import com.example.demo.house.model.SidoGugunCodeDto;
import com.example.demo.house.service.HouseMapService;
import com.example.demo.interest.service.InterestService;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/interest")
public class InterestController {
	
	@Autowired
	private InterestService interestService;

	@PostMapping
	public ResponseEntity<String> insertInterst(
			@RequestParam("id") int id,
			@RequestParam("aptCode") int aptCode,
			@RequestParam("housedealNo") int housedealNo
			) throws Exception {
		Map<String,Object> map=new HashMap<>();
		map.put("memberId", id);
		map.put("aptCode", aptCode);
		map.put("housedealNo", housedealNo);
		int cnt = interestService.insertInterest(map);
		String res=null;
		if (cnt > 0) {
			 res = "success";
		} else {
			res = "fail";
		}
		return new ResponseEntity<String>(res, HttpStatus.OK);
	}
	
	
	@PostMapping("/list")
	public ResponseEntity<List<HouseInfoDto>> interestList(
			@RequestParam("id") int id
			) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(interestService.interestList(id), HttpStatus.OK);
	}
}

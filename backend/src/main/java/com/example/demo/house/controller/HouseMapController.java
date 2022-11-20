package com.example.demo.house.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.house.model.HouseInfoDto;
import com.example.demo.house.model.SidoGugunCodeDto;
import com.example.demo.house.service.HouseMapService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/map")
@Api("Map 컨트롤러 API V1")
public class HouseMapController {

	@Autowired
	private HouseMapService houseMapService;

	@ApiOperation(value = "시도 정보", notes = "전국의 시도를 반환한다.", response = List.class)
	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
		return new ResponseEntity<List<SidoGugunCodeDto>>(houseMapService.getSido(), HttpStatus.OK);
	}

	@ApiOperation(value = "구군 정보", notes = "전국의 구군을 반환한다.", response = List.class)
	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunCodeDto>> gugun(
			@RequestParam("sido") @ApiParam(value = "시도코드.", required = true) String sido) throws Exception {
		return new ResponseEntity<List<SidoGugunCodeDto>>(houseMapService.getGugunInSido(sido), HttpStatus.OK);
	}

	@ApiOperation(value = "동 정보", notes = "해당 구군의 거래기록이 존재하는 동을 반환한다.", response = List.class)
	@GetMapping("/dong")
	public ResponseEntity<List<HouseInfoDto>> dong(@RequestParam("gugun") @ApiParam(value = "구군코드.", required = true) String gugun) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(houseMapService.getDongInGugun(gugun), HttpStatus.OK);
	}
	
	@ApiOperation(value = "년도 정보", notes = "해당 동의 거래기록이 존재하는 년도를 반환한다.", response = List.class)
	@GetMapping("/year")
	public ResponseEntity<List<String>> year(@RequestParam("dong") @ApiParam(value = "동코드.", required = true) String dong) throws Exception {
		return new ResponseEntity<List<String>>(houseMapService.getYear(dong), HttpStatus.OK);
	}
	
	@ApiOperation(value = "월 정보", notes = "해당 동, 해당 년도의 거래기록이 존재하는 월을 반환한다.", response = List.class)
	@GetMapping("/month")
	public ResponseEntity<List<String>> month(
			@RequestParam("dong") @ApiParam(value = "동코드.", required = true) String dong,
			@RequestParam("year") @ApiParam(value = "년도.", required = true) String year) throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("dong",dong);
		map.put("year", year);
		return new ResponseEntity<List<String>>(houseMapService.getMonth(map), HttpStatus.OK);
	}
	
	@ApiOperation(value = "집 정보", notes = "해당 동, 해당 년도, 해당 월의 거래된 집을 반환한다.", response = List.class)
	@GetMapping("/apt")
	public ResponseEntity<List<HouseInfoDto>> apt(
			@RequestParam("dong") @ApiParam(value = "동코드.", required = true) String dong,
			@RequestParam("year") @ApiParam(value = "거래년도.", required = true) String year,
			@RequestParam("month") @ApiParam(value = "거래월.", required = true) String month
			) throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("dong",dong);
		map.put("year", year);
		map.put("month", month);	
		return new ResponseEntity<List<HouseInfoDto>>(houseMapService.getApt(map), HttpStatus.OK);
	}

}

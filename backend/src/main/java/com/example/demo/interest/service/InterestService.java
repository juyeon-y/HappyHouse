package com.example.demo.interest.service;

import java.util.List;
import java.util.Map;

import com.example.demo.house.model.HouseInfoDto;

public interface InterestService {

	int insertInterest(Map<String, Object> map) throws Exception;
	List<HouseInfoDto> interestList(int id) throws Exception;
	
}

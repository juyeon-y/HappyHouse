package com.example.demo.house.service;

import java.util.List;
import java.util.Map;

import com.example.demo.house.model.SidoGugunCodeDto;
import com.example.demo.house.model.HouseInfoDto;

public interface HouseMapService {

	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;
	List<String> getYear(String dong) throws Exception;
	List<String> getMonth(Map<String, String> map) throws Exception;
	List<HouseInfoDto> getApt(Map<String, String> map) throws Exception;
	
}

package com.example.demo.house.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.house.model.SidoGugunCodeDto;
import com.example.demo.house.model.mapper.HouseMapMapper;
import com.example.demo.house.model.HouseInfoDto;

@Service
public class HouseMapServiceImpl implements HouseMapService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getSido();
	}
	
	@Override
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getGugunInSido(sido);
	}
	
	@Override
	public List<HouseInfoDto> getDongInGugun(String gugun) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getDongInGugun(gugun);
	}

	@Override
	public List<String> getYear(String dong) throws Exception{
		return sqlSession.getMapper(HouseMapMapper.class).getYear(dong);
	}

	@Override
	public List<String> getMonth(Map<String, String> map) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getMonth(map);
	}

	@Override
	public List<HouseInfoDto> getApt(Map<String, String> map) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getApt(map);
	}


}

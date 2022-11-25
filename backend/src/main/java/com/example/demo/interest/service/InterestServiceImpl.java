package com.example.demo.interest.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.house.model.HouseInfoDto;
import com.example.demo.interest.model.mapper.InterestMapper;
@Service
public class InterestServiceImpl implements InterestService{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insertInterest(Map<String, Object> map) throws Exception {
		return sqlSession.getMapper(InterestMapper.class).insertInterest(map);
	}

	@Override
	public List<HouseInfoDto> interestList(int id) throws Exception {
		
		return sqlSession.getMapper(InterestMapper.class).interestList(id);
	}

}

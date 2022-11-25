package com.example.demo.interest.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.house.model.HouseInfoDto;

@Mapper
public interface InterestMapper {

	int insertInterest(Map<String, Object> map) throws SQLException;

	List<HouseInfoDto> interestList(int id) throws SQLException;

}

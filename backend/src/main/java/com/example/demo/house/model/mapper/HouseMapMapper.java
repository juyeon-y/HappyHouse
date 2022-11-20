package com.example.demo.house.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.house.model.HouseInfoDto;
import com.example.demo.house.model.SidoGugunCodeDto;

@Mapper
public interface HouseMapMapper {

	List<SidoGugunCodeDto> getSido() throws SQLException;

	List<SidoGugunCodeDto> getGugunInSido(String sido) throws SQLException;

	List<HouseInfoDto> getDongInGugun(String gugun) throws SQLException;

	List<String> getYear(String dong) throws SQLException;

	List<String> getMonth(Map<String, String> map) throws SQLException;

	List<HouseInfoDto> getApt(Map<String, String> map) throws SQLException;

}

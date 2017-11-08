package org.spring.springboot.dao;

import org.spring.springboot.domain.ElectricStation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ElectricStationMapper {
    int deleteByPrimaryKey(Integer iStationId);

    int insert(ElectricStation record);

    int insertSelective(ElectricStation record);

    ElectricStation selectByPrimaryKey(Integer iStationId);

    int updateByPrimaryKeySelective(ElectricStation record);

    int updateByPrimaryKey(ElectricStation record);

    int insert4New(ElectricStation record);
    List<Map<String ,Integer>> selectStationIdList();
}
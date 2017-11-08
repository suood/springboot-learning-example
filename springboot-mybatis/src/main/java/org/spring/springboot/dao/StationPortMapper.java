package org.spring.springboot.dao;


import org.spring.springboot.domain.StationPort;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StationPortMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(StationPort record);

    int insertSelective(StationPort record);

    StationPort selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StationPort record);

    int updateByPrimaryKey(StationPort record);

    int insertBench(@Param("list") List<StationPort> list);
}
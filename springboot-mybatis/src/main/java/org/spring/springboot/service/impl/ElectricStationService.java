package org.spring.springboot.service.impl;

import org.spring.springboot.dao.ElectricStationMapper;
import org.spring.springboot.domain.ElectricStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("ElectricStationService")
public class ElectricStationService {

    @Autowired
    private ElectricStationMapper electricStationMapper;

    public int insertOrUpdate(ElectricStation electricStation){
        int rows = electricStationMapper.insert4New(electricStation);
        if (rows<1){
            rows = electricStationMapper.updateByPrimaryKeySelective(electricStation);
            return rows;
        }else {
            return rows;
        }
    }
    public List<Map<String,Integer>> getStationIdList(){
        return electricStationMapper.selectStationIdList();
    }
}

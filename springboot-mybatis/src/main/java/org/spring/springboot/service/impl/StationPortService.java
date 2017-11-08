package org.spring.springboot.service.impl;

import org.spring.springboot.dao.StationPortMapper;
import org.spring.springboot.domain.StationPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("stationPortService")
public class StationPortService {
    @Autowired
    private StationPortMapper stationPortMapper;

    public int insertStationPort(StationPort record){
        return stationPortMapper.insertSelective( record);
    }
    public int insertBench(List<StationPort> stationPortList){
        return stationPortMapper.insertBench(stationPortList);
    }
}

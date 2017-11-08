package org.spring.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StationPort {

    private Integer id;


    private Integer iOutletId;


    private String vOutletName;


    private Integer iStationId;

    private String vOutletNo;

    private Integer iOutletSerialNo;

    private Integer iState;

    private Integer iCurrentChargingRecordId;

    private String tAddTime;

    private Date spiderTime;

    private Integer iTotalChargingCount;

    private Integer lTotalChargingTime;

}
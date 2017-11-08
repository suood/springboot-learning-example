package org.spring.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ElectricStation {
    private Integer iStationId;

    private String vStationName;

    private Integer iManagerId;

    private String vStationNo;

    private String vStationMac;

    private Integer iSwitchType;

    private Integer iState;

    private Integer iHardWareState;

    private String tOpenTime;

    private Integer iServerId;

    private String vHelpMobile;

    private String vTelephone;

    private String tAddTime;

    private String dTotalIncome;

    private Integer iUpdateManagerId;

    private String tUpdateTime;

    private String vAddress;

    private String dBMapX;

    private String dBMapY;

    private Integer iOrderVersion;

    private Integer iInstallType;

    private String vImgPath;

    private Integer iAreaId;

    private String vAreaName;

    private String dActivePowerLimit;

    private String vSignalValue;

    private String dTotalCurrent;

    private String dTotalActivePower;

    private String dTotalEnergy;

    private String tStatusTime;

    private Integer totalOutletCount;

    private Integer usedOutletCount;

    private String dFeePerMin;

    private String fzFunds;


}
package com.paladin.qos.service.data.vo;

import com.paladin.qos.analysis.DataConstantContainer;

public class BedReportVO {

    private String unitId;

    private String unitName;

    private Integer bedNumber;

    private Long openBedNumber;

    private Long userBedNumber;

    private Double userBedRate;

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return DataConstantContainer.getUnitName(unitName);
    }
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Integer getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(Integer bedNumber) {
        this.bedNumber = bedNumber;
    }

    public Long getOpenBedNumber() {
        return openBedNumber;
    }

    public void setOpenBedNumber(Long openBedNumber) {
        this.openBedNumber = openBedNumber;
    }

    public Long getUserBedNumber() {
        return userBedNumber;
    }

    public void setUserBedNumber(Long userBedNumber) {
        this.userBedNumber = userBedNumber;
    }

    public Double getUserBedRate() {
        return userBedRate;
    }

    public void setUserBedRate(Double userBedRate) {
        this.userBedRate = userBedRate;
    }
}

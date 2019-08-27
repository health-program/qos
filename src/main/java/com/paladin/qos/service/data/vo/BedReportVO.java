package com.paladin.qos.service.data.vo;

public class BedReportVO {

    private String unitName;

    private Integer bedNumber;

    private Integer openBedNumber;

    private Integer userBedNumber;

    private Double userBedRate;

    public String getUnitName() {
        return unitName;
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

    public Integer getOpenBedNumber() {
        return openBedNumber;
    }

    public void setOpenBedNumber(Integer openBedNumber) {
        this.openBedNumber = openBedNumber;
    }

    public Integer getUserBedNumber() {
        return userBedNumber;
    }

    public void setUserBedNumber(Integer userBedNumber) {
        this.userBedNumber = userBedNumber;
    }

    public Double getUserBedRate() {
        return userBedRate;
    }

    public void setUserBedRate(Double userBedRate) {
        this.userBedRate = userBedRate;
    }
}

package com.paladin.qos.service.gongwei.vo;


import com.paladin.qos.analysis.DataConstantContainer;

public class PressureSugarManagementVO {

    private String unitId;

    private Long pressureNumber;

    private Long pressureManageNumber;

    private Long pressureFollowNumber;

    private Long pressureRecentReach;

    private Double pressureControlRate;

    private Double pressureManageRate;

    private Long sugarNumber;

    private Long sugarManageNumber;

    private Long sugarFollowNumber;

    private Long sugarRecentReach;

    private Double sugarControlRate;

    private Double sugarManageRate;

    public Long getPressureFollowNumber() {
        return pressureFollowNumber;
    }

    public void setPressureFollowNumber(Long pressureFollowNumber) {
        this.pressureFollowNumber = pressureFollowNumber;
    }

    public Long getSugarFollowNumber() {
        return sugarFollowNumber;
    }

    public void setSugarFollowNumber(Long sugarFollowNumber) {
        this.sugarFollowNumber = sugarFollowNumber;
    }

    public Long getPressureRecentReach() {
        return pressureRecentReach;
    }

    public void setPressureRecentReach(Long pressureRecentReach) {
        this.pressureRecentReach = pressureRecentReach;
    }

    public Long getSugarRecentReach() {
        return sugarRecentReach;
    }

    public void setSugarRecentReach(Long sugarRecentReach) {
        this.sugarRecentReach = sugarRecentReach;
    }

    public String getUnitName() {
        return DataConstantContainer.getUnitName(unitId);
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public Long getPressureNumber() {
        return pressureNumber;
    }

    public void setPressureNumber(Long pressureNumber) {
        this.pressureNumber = pressureNumber;
    }

    public Long getPressureManageNumber() {
        return pressureManageNumber;
    }

    public void setPressureManageNumber(Long pressureManageNumber) {
        this.pressureManageNumber = pressureManageNumber;
    }

    public Double getPressureControlRate() {
        return pressureControlRate;
    }

    public void setPressureControlRate(Double pressureControlRate) {
        this.pressureControlRate = pressureControlRate;
    }

    public Double getPressureManageRate() {
        return pressureManageRate;
    }

    public void setPressureManageRate(Double pressureManageRate) {
        this.pressureManageRate = pressureManageRate;
    }

    public Long getSugarNumber() {
        return sugarNumber;
    }

    public void setSugarNumber(Long sugarNumber) {
        this.sugarNumber = sugarNumber;
    }

    public Long getSugarManageNumber() {
        return sugarManageNumber;
    }

    public void setSugarManageNumber(Long sugarManageNumber) {
        this.sugarManageNumber = sugarManageNumber;
    }

    public Double getSugarControlRate() {
        return sugarControlRate;
    }

    public void setSugarControlRate(Double sugarControlRate) {
        this.sugarControlRate = sugarControlRate;
    }

    public Double getSugarManageRate() {
        return sugarManageRate;
    }

    public void setSugarManageRate(Double sugarManageRate) {
        this.sugarManageRate = sugarManageRate;
    }
}

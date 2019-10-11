package com.paladin.qos.model.gongwei;

public class PressureSugar {

    private String unitId;

    private Long pressureNumber;

    private Long pressureManageNumber;

    private Long pressureFollowNumber;

    private Long sugarNumber;

    private Long sugarManageNumber;

    private Long sugarFollowNumber;

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

    public String getUnitId() {
        return unitId;
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
}

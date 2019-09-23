package com.paladin.qos.service.gongwei.vo;


import com.paladin.qos.analysis.DataConstantContainer;

public class PhysicalManagementVO {

    private String unitId;

    private Long oldPeopleNumber;

    private Long physicalNumber;

    private Double physicalRate;

    private Long completePhysicalNumber;

    private Double healthManageRate;

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return DataConstantContainer.getUnitName(unitId);
    }


    public Long getOldPeopleNumber() {
        return oldPeopleNumber;
    }

    public void setOldPeopleNumber(Long oldPeopleNumber) {
        this.oldPeopleNumber = oldPeopleNumber;
    }

    public Long getPhysicalNumber() {
        return physicalNumber;
    }

    public void setPhysicalNumber(Long physicalNumber) {
        this.physicalNumber = physicalNumber;
    }

    public Double getPhysicalRate() {
        return physicalRate;
    }

    public void setPhysicalRate(Double physicalRate) {
        this.physicalRate = physicalRate;
    }

    public Long getCompletePhysicalNumber() {
        return completePhysicalNumber;
    }

    public void setCompletePhysicalNumber(Long completePhysicalNumber) {
        this.completePhysicalNumber = completePhysicalNumber;
    }

    public Double getHealthManageRate() {
        return healthManageRate;
    }

    public void setHealthManageRate(Double healthManageRate) {
        this.healthManageRate = healthManageRate;
    }
}

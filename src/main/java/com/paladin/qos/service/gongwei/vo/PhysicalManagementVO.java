package com.paladin.qos.service.gongwei.vo;


public class PhysicalManagementVO {

    private String managedCenter;

    private Long oldPeopleNumber;

    private Long physicalNumber;

    private Double physicalRate;

    private Long completePhysicalNumber;

    private Double healthManageRate;

    public String getManagedCenter() {
        return managedCenter;
    }

    public void setManagedCenter(String managedCenter) {
        this.managedCenter = managedCenter;
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

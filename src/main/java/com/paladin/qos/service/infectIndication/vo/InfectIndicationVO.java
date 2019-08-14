package com.paladin.qos.service.infectIndication.vo;


import java.util.Date;

public class InfectIndicationVO {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String totalInfection;//医院感染总发生率统计

    private String operatingDepartmentsInfection;//与手术相关科室感染发生率统计

    private String operatingPartInfection;//手术部位感染总发生率统计

    private String operatingRiskInfection;//手术风险分级（NNIS分级）手术部位感染率统计

    private String lungInfection;//手术患者肺部感染发生率统计

    private String newBornInfection;//新生儿患者医院感染发生率统计

    private String  selectiveOperationsInfection;//择期手术患者医院感染发生率统计

    private String bloodInfection;//与血液透析相关血液感染发生率统计

    private String intensiveCareUnitInfection;//重症监护室相关感染率统计

    private Date createTime;

    private String createUserId;

    private Date updateTime;

    private String updateUserId;

    public String getTotalInfection() {
        return totalInfection;
    }

    public void setTotalInfection(String totalInfection) {
        this.totalInfection = totalInfection;
    }

    public String getOperatingDepartmentsInfection() {
        return operatingDepartmentsInfection;
    }

    public void setOperatingDepartmentsInfection(String operatingDepartmentsInfection) {
        this.operatingDepartmentsInfection = operatingDepartmentsInfection;
    }

    public String getOperatingPartInfection() {
        return operatingPartInfection;
    }

    public void setOperatingPartInfection(String operatingPartInfection) {
        this.operatingPartInfection = operatingPartInfection;
    }

    public String getOperatingRiskInfection() {
        return operatingRiskInfection;
    }

    public void setOperatingRiskInfection(String operatingRiskInfection) {
        this.operatingRiskInfection = operatingRiskInfection;
    }

    public String getLungInfection() {
        return lungInfection;
    }

    public void setLungInfection(String lungInfection) {
        this.lungInfection = lungInfection;
    }

    public String getNewBornInfection() {
        return newBornInfection;
    }

    public void setNewBornInfection(String newBornInfection) {
        this.newBornInfection = newBornInfection;
    }

    public String getSelectiveOperationsInfection() {
        return selectiveOperationsInfection;
    }

    public void setSelectiveOperationsInfection(String selectiveOperationsInfection) {
        this.selectiveOperationsInfection = selectiveOperationsInfection;
    }

    public String getBloodInfection() {
        return bloodInfection;
    }

    public void setBloodInfection(String bloodInfection) {
        this.bloodInfection = bloodInfection;
    }

    public String getIntensiveCareUnitInfection() {
        return intensiveCareUnitInfection;
    }

    public void setIntensiveCareUnitInfection(String intensiveCareUnitInfection) {
        this.intensiveCareUnitInfection = intensiveCareUnitInfection;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }
    
    
}

package com.paladin.qos.service.infectioncomplication.dto;

import java.util.Date;


public class InfectionDTO {

    private String id;

    private Double totalInfection;//医院感染总发生率统计

    private Double operatingDepartmentsInfection;//与手术相关科室感染发生率统计

    private Double operatingPartInfection;//手术部位感染总发生率统计

    private Double operatingRiskInfection1;//手术风险1级（NNIS分级）手术部位感染率统计

    private Double operatingRiskInfection2;//手术风险2级（NNIS分级）手术部位感染率统计
    
    private Double operatingRiskInfection3;//手术风险3级（NNIS分级）手术部位感染率统计

    private Double lungInfection;//手术患者肺部感染发生率统计

    private Double newBornInfection;//新生儿患者医院感染发生率统计

    private Double  selectiveOperationsInfection;//择期手术患者医院感染发生率统计

    private Double bloodInfection;//与血液透析相关血液感染发生率统计

    private Double intensiveCareUnitInfection;//重症监护室相关感染率统计

    private Date createTime;

    private String createUserId;

    private Date updateTime;

    private String updateUserId;

    private String unitId;

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getTotalInfection() {
        return totalInfection;
    }

    public void setTotalInfection(Double totalInfection) {
        this.totalInfection = totalInfection;
    }

    public Double getOperatingDepartmentsInfection() {
        return operatingDepartmentsInfection;
    }

    public void setOperatingDepartmentsInfection(Double operatingDepartmentsInfection) {
        this.operatingDepartmentsInfection = operatingDepartmentsInfection;
    }

    public Double getOperatingPartInfection() {
        return operatingPartInfection;
    }

    public void setOperatingPartInfection(Double operatingPartInfection) {
        this.operatingPartInfection = operatingPartInfection;
    }

    public Double getOperatingRiskInfection1() {
        return operatingRiskInfection1;
    }

    public void setOperatingRiskInfection1(Double operatingRiskInfection1) {
        this.operatingRiskInfection1 = operatingRiskInfection1;
    }

    public Double getOperatingRiskInfection2() {
        return operatingRiskInfection2;
    }

    public void setOperatingRiskInfection2(Double operatingRiskInfection2) {
        this.operatingRiskInfection2 = operatingRiskInfection2;
    }

    public Double getOperatingRiskInfection3() {
        return operatingRiskInfection3;
    }

    public void setOperatingRiskInfection3(Double operatingRiskInfection3) {
        this.operatingRiskInfection3 = operatingRiskInfection3;
    }

    public Double getLungInfection() {
        return lungInfection;
    }

    public void setLungInfection(Double lungInfection) {
        this.lungInfection = lungInfection;
    }

    public Double getNewBornInfection() {
        return newBornInfection;
    }

    public void setNewBornInfection(Double newBornInfection) {
        this.newBornInfection = newBornInfection;
    }

    public Double getSelectiveOperationsInfection() {
        return selectiveOperationsInfection;
    }

    public void setSelectiveOperationsInfection(Double selectiveOperationsInfection) {
        this.selectiveOperationsInfection = selectiveOperationsInfection;
    }

    public Double getBloodInfection() {
        return bloodInfection;
    }

    public void setBloodInfection(Double bloodInfection) {
        this.bloodInfection = bloodInfection;
    }

    public Double getIntensiveCareUnitInfection() {
        return intensiveCareUnitInfection;
    }

    public void setIntensiveCareUnitInfection(Double intensiveCareUnitInfection) {
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
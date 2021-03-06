package com.paladin.qos.service.infectioncomplication.dto;

import com.paladin.framework.common.OffsetPage;

import java.util.Date;

/**
 * @author wcw
 * @version 2019年8月8日 下午15:34:10
 */
public class InfectionQuery extends OffsetPage {



    private Double totalInfection;//医院感染总发生率统计
    
    private Double operatingDepartmentsInfection;//与手术相关科室感染发生率统计
    
    private Double operatingPartInfection;//手术部位感染总发生率统计

    private Double operatingRiskInfection;//手术风险分级（NNIS分级）手术部位感染率统计

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
    
    private String date;

    private String [] unitIds;

    public String[] getUnitIds() {
        return unitIds;
    }

    public void setUnitIds(String[] unitIds) {
        this.unitIds = unitIds;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
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

    public Double getOperatingRiskInfection() {
        return operatingRiskInfection;
    }

    public void setOperatingRiskInfection(Double operatingRiskInfection) {
        this.operatingRiskInfection = operatingRiskInfection;
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

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }
}
package com.paladin.qos.service.count.vo;

import com.paladin.qos.analysis.DataConstantContainer;

import java.util.Date;

public class CountReferralVO {

    private String id;

    private Integer upOutNumber;

    private Integer isSigning;

    private Integer downOutNumber;

    private Date createTime;

    private String createUserId;

    private Date updateTime;

    private String updateUserId;

    private String  unitId;

    private String unitName;

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return DataConstantContainer.getUnitName(unitId);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUpOutNumber() {
        return upOutNumber;
    }

    public void setUpOutNumber(Integer upOutNumber) {
        this.upOutNumber = upOutNumber;
    }

    public Integer getDownOutNumber() {
        return downOutNumber;
    }

    public void setDownOutNumber(Integer downOutNumber) {
        this.downOutNumber = downOutNumber;
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

    public Integer getIsSigning() {
        return isSigning;
    }

    public void setIsSigning(Integer isSigning) {
        this.isSigning = isSigning;
    }
}
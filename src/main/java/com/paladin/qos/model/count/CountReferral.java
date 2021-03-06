package com.paladin.qos.model.count;

import com.paladin.framework.common.BaseModel;

import javax.persistence.Id;

public class CountReferral extends BaseModel {

    public static final String COLUMN_IS_SIGNING = "isSigning";

    @Id
    private String id;

    private Integer upOutNumber;

    private Integer downOutNumber;

    private Integer isSigning;

    private String  unitId;

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

    public Integer getIsSigning() {
        return isSigning;
    }

    public void setIsSigning(Integer isSigning) {
        this.isSigning = isSigning;
    }
}

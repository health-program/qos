package com.paladin.qos.service.count.dto;


public class CountReferralDTO {


    private String id;

    private Integer upOutNumber;

    private Integer downOutNumber;

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


}
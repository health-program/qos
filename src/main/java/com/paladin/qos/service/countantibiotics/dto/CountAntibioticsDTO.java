package com.paladin.qos.service.countantibiotics.dto;

import com.google.common.base.Strings;
import com.paladin.framework.common.OffsetPage;

public class CountAntibioticsDTO extends OffsetPage {
    
    private String id;
    private Integer varieties;
    private Double averageCost;
    private Double userRate;
    private Double rateOfTotal;
    private String unitId;
    private String inputDate;

    public String getInputDate() {
        return inputDate;
    }

    public void setInputDate(String inputDate) {
        this.inputDate = inputDate;
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public Integer getVarieties() {
	return varieties;
    }

    public void setVarieties(Integer varieties) {
	this.varieties = varieties;
    }

    public Double getAverageCost() {
	return averageCost;
    }

    public void setAverageCost(Double averageCost) {
	this.averageCost = averageCost;
    }

    public Double getUserRate() {
	return userRate;
    }

    public void setUserRate(Double userRate) {
	this.userRate = userRate;
    }

    public Double getRateOfTotal() {
	return rateOfTotal;
    }

    public void setRateOfTotal(Double rateOfTotal) {
	this.rateOfTotal = rateOfTotal;
    }

    public String getUnitId() {
	return unitId;
    }

    public void setUnitId(String unitId) {
	this.unitId = unitId;
    }

    @Override
    public String toString() {
        return "记录ID:" + ( Strings.isNullOrEmpty(id) ? '无' : id);
    }

}

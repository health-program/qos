package com.paladin.qos.service.infectioncomplication.vo;

import java.util.Date;

import com.paladin.qos.analysis.DataConstantContainer;

public class OperationComplicationVO {
    
    private String id;
    private Date createTime;
    private Double embolism;
    private Double pulmonaryEmbolism;
    private Double venousThrombosis;
    private Double hematoma;
    private Double woundDehiscence;
    private Double suddenDeath;
    private Double operativeComplications;
    private Double anaesthesia;
    private String unitId;
    private String date;
    private String unitName;

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public Date getCreateTime() {
	return createTime;
    }

    public void setCreateTime(Date createTime) {
	this.createTime = createTime;
    }

    public Double getEmbolism() {
	return embolism;
    }

    public void setEmbolism(Double embolism) {
	this.embolism = embolism;
    }

    public Double getPulmonaryEmbolism() {
	return pulmonaryEmbolism;
    }

    public void setPulmonaryEmbolism(Double pulmonaryEmbolism) {
	this.pulmonaryEmbolism = pulmonaryEmbolism;
    }

    public Double getVenousThrombosis() {
	return venousThrombosis;
    }

    public void setVenousThrombosis(Double venousThrombosis) {
	this.venousThrombosis = venousThrombosis;
    }

    public Double getHematoma() {
	return hematoma;
    }

    public void setHematoma(Double hematoma) {
	this.hematoma = hematoma;
    }

    public Double getWoundDehiscence() {
	return woundDehiscence;
    }

    public void setWoundDehiscence(Double woundDehiscence) {
	this.woundDehiscence = woundDehiscence;
    }

    public Double getSuddenDeath() {
	return suddenDeath;
    }

    public void setSuddenDeath(Double suddenDeath) {
	this.suddenDeath = suddenDeath;
    }

    public Double getOperativeComplications() {
	return operativeComplications;
    }

    public void setOperativeComplications(Double operativeComplications) {
	this.operativeComplications = operativeComplications;
    }

    public Double getAnaesthesia() {
	return anaesthesia;
    }

    public void setAnaesthesia(Double anaesthesia) {
	this.anaesthesia = anaesthesia;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

}

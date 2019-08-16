package com.paladin.qos.service.infectionAndComplication.vo;

import java.util.Date;

public class OperationComplicationVO {
    public String id;
    public String createUserId;
    public String updateUserId;
    public Date createTime;
	public Date updateTime;
	public Double embolism;
	public Double pulmonaryEmbolism;
	public Double venousThrombosis;
	public Double hematoma;
	public Double woundDehiscence;
	public Double suddenDeath;
	public Double operativeComplications;
	public Double anaesthesia;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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

}

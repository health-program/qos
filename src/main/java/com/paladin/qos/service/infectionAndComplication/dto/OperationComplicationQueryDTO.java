package com.paladin.qos.service.infectionAndComplication.dto;

import com.paladin.framework.common.OffsetPage;

import java.util.Date;

public class OperationComplicationQueryDTO extends  OffsetPage {
	public String embolism;
    public Date createTime;
    public String createUserId;
    public Date updateTime;
    public String updateUserId;
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
	
	public String pulmonaryEmbolism;
     public String venousThrombosis;
     public String hematoma;
     public String woundDehiscence;
     public String suddenDeath;
     public String operativeComplications;
     public String anaesthesia;
	public String getEmbolism() {
		return embolism;
	}
	public void setEmbolism(String embolism) {
		this.embolism = embolism;
	}
	public String getPulmonaryEmbolism() {
		return pulmonaryEmbolism;
	}
	public void setPulmonaryEmbolism(String pulmonaryEmbolism) {
		this.pulmonaryEmbolism = pulmonaryEmbolism;
	}
	public String getVenousThrombosis() {
		return venousThrombosis;
	}
	public void setVenousThrombosis(String venousThrombosis) {
		this.venousThrombosis = venousThrombosis;
	}
	public String getHematoma() {
		return hematoma;
	}
	public void setHematoma(String hematoma) {
		this.hematoma = hematoma;
	}
	public String getWoundDehiscence() {
		return woundDehiscence;
	}
	public void setWoundDehiscence(String woundDehiscence) {
		this.woundDehiscence = woundDehiscence;
	}
	public String getSuddenDeath() {
		return suddenDeath;
	}
	public void setSuddenDeath(String suddenDeath) {
		this.suddenDeath = suddenDeath;
	}
	public String getOperativeComplications() {
		return operativeComplications;
	}
	public void setOperativeComplications(String operativeComplications) {
		this.operativeComplications = operativeComplications;
	}
	public String getAnaesthesia() {
		return anaesthesia;
	}
	public void setAnaesthesia(String anaesthesia) {
		this.anaesthesia = anaesthesia;
	}
	 
}

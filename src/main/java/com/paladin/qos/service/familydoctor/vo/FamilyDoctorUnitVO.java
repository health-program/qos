package com.paladin.qos.service.familydoctor.vo;

import java.util.Date;

public class FamilyDoctorUnitVO {

	// 
	private String id;

	// 单位名称
	private String unit;

	// 人口数
	private String population;
	
	// 工作室数量
	private Integer studioNum;
	
	// 工作室名称
	private String studioName;

	// 
	private Date createTime;

	// 
	private String createUserId;

	// 
	private Date updateTime;

	// 
	private String updateUserId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getPopulation() {
		return population;
	}

	public void setPopulation(String population) {
		this.population = population;
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

	public Integer getStudioNum() {
	    return studioNum;
	}

	public void setStudioNum(Integer studioNum) {
	    this.studioNum = studioNum;
	}

	public String getStudioName() {
	    return studioName;
	}

	public void setStudioName(String studioName) {
	    this.studioName = studioName;
	}

}
package com.paladin.qos.service.familydoctor.vo;

import java.util.Date;

public class FamilyDoctorTeamVO {

	// 
	private String id;

	// 团队名称
	private String teamName;

	// 单位ID
	private String unitId;

	// 服务责任小区（自然村）
	private String serviceDistrict;

	// 服务区域常住人口数
	private Integer servicePopulation;

	// 经纬度
	private String coordinate;

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

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getUnitId() {
	    return unitId;
	}

	public void setUnitId(String unitId) {
	    this.unitId = unitId;
	}

	public String getServiceDistrict() {
		return serviceDistrict;
	}

	public void setServiceDistrict(String serviceDistrict) {
		this.serviceDistrict = serviceDistrict;
	}

	public Integer getServicePopulation() {
		return servicePopulation;
	}

	public void setServicePopulation(Integer servicePopulation) {
		this.servicePopulation = servicePopulation;
	}

	public String getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
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
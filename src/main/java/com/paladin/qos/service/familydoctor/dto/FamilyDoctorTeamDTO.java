package com.paladin.qos.service.familydoctor.dto;


public class FamilyDoctorTeamDTO {

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

	@Override
	public String toString() {
		return "家庭医生团队名称:" + teamName;
	}
}
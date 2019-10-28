package com.paladin.qos.service.familydoctor.dto;


public class FamilyDoctorUnitDTO {

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

	@Override
	public String toString() {
		return "家庭医生社区名称:" + unit;
	}
	
}
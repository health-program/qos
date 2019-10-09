package com.paladin.qos.service.familydoctor.vo;

import com.paladin.qos.analysis.DataConstantContainer;

public class DataFamilyDoctorPersonnelVO {

	// 
	private String id;
	
	// 单位ID
	private String unitId;
	
	// 团队名称
	private String teamName;

	// 姓名
	private String name;

	// 性别
	private Integer sex;

	// 年龄
	private Integer age;

	// 是否团队长
	private Integer isTeamCaptain;

	// 职称
	private String jobRank;
	
	//人员类别
	private String personnelCategory;
	
	public String getUnitName() {
	   return DataConstantContainer.getUnitName(unitId);
	}

	public String getId() {
	    return id;
	}

	public void setId(String id) {
	    this.id = id;
	}

	public String getUnitId() {
	    return unitId;
	}

	public void setUnitId(String unitId) {
	    this.unitId = unitId;
	}

	public String getTeamName() {
	    return teamName;
	}

	public void setTeamName(String teamName) {
	    this.teamName = teamName;
	}

	public String getName() {
	    return name;
	}

	public void setName(String name) {
	    this.name = name;
	}

	public Integer getSex() {
	    return sex;
	}

	public void setSex(Integer sex) {
	    this.sex = sex;
	}

	public Integer getAge() {
	    return age;
	}

	public void setAge(Integer age) {
	    this.age = age;
	}

	public Integer getIsTeamCaptain() {
	    return isTeamCaptain;
	}

	public void setIsTeamCaptain(Integer isTeamCaptain) {
	    this.isTeamCaptain = isTeamCaptain;
	}

	public String getJobRank() {
	    return jobRank;
	}

	public void setJobRank(String jobRank) {
	    this.jobRank = jobRank;
	}

	public String getPersonnelCategory() {
	    return personnelCategory;
	}

	public void setPersonnelCategory(String personnelCategory) {
	    this.personnelCategory = personnelCategory;
	}
}
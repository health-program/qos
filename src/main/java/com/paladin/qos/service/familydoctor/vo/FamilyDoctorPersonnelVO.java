package com.paladin.qos.service.familydoctor.vo;

import java.util.Date;

public class FamilyDoctorPersonnelVO {

	// 
	private String id;

	// 姓名
	private String name;

	// 性别
	private Integer sex;

	// 年龄
	private Integer age;

	// 学历
	private Integer oeducation;

	// 职称
	private Integer jobRank;
	
	//人员类别
	private Integer personnelCategory;

	// 二级及以上医院专科团队医生（医院+医生）
	private String specialtyTeamDoctor;

	// 其他人员（请注明）
	private String otherPersonnel;

	// 团队ID
	private String teamId;

	// 单位ID
	private String unitId;

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

	public Integer getOeducation() {
		return oeducation;
	}

	public void setOeducation(Integer oeducation) {
		this.oeducation = oeducation;
	}

	public Integer getJobRank() {
		return jobRank;
	}

	public void setJobRank(Integer jobRank) {
		this.jobRank = jobRank;
	}

	public String getSpecialtyTeamDoctor() {
		return specialtyTeamDoctor;
	}

	public void setSpecialtyTeamDoctor(String specialtyTeamDoctor) {
		this.specialtyTeamDoctor = specialtyTeamDoctor;
	}

	public String getOtherPersonnel() {
		return otherPersonnel;
	}

	public void setOtherPersonnel(String otherPersonnel) {
		this.otherPersonnel = otherPersonnel;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
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

	public Integer getPersonnelCategory() {
	    return personnelCategory;
	}

	public void setPersonnelCategory(Integer personnelCategory) {
	    this.personnelCategory = personnelCategory;
	}

}
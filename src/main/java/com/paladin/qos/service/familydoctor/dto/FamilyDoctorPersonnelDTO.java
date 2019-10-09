package com.paladin.qos.service.familydoctor.dto;

public class FamilyDoctorPersonnelDTO {

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
	
	// 是否团队长
	private Integer isTeamCaptain;

	// 职称
	private Integer jobRank;
	
	//人员类别
	private Integer personnelCategory;

	// 二级及以上医院专科团队医生（医院+医生）
	private String specialtyTeamDoctor;

	// 其他人员（请注明）
	private String otherPersonnel;

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

	public Integer getPersonnelCategory() {
	    return personnelCategory;
	}

	public void setPersonnelCategory(Integer personnelCategory) {
	    this.personnelCategory = personnelCategory;
	}

	public Integer getIsTeamCaptain() {
	    return isTeamCaptain;
	}

	public void setIsTeamCaptain(Integer isTeamCaptain) {
	    this.isTeamCaptain = isTeamCaptain;
	}

}
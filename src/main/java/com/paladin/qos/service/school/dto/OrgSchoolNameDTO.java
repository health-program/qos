package com.paladin.qos.service.school.dto;

public class OrgSchoolNameDTO {

	// 
	private String id;

	// 学校全称
	private String schoolFullName;
	
	private String parentId;
	
	private String level;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSchoolFullName() {
		return schoolFullName;
	}

	public void setSchoolFullName(String schoolFullName) {
		this.schoolFullName = schoolFullName;
	}

	public String getParentId() {
	    return parentId;
	}

	public void setParentId(String parentId) {
	    this.parentId = parentId;
	}

	public String getLevel() {
	    return level;
	}

	public void setLevel(String level) {
	    this.level = level;
	}

}
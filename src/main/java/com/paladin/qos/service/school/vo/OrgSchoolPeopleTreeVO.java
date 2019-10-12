package com.paladin.qos.service.school.vo;

import java.util.Date;

public class OrgSchoolPeopleTreeVO {

	// 主键
	private String id;

	// 学校ID
	private String parentId;

	// 学段
	private String name;
	
	//级别
	private String level;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
}
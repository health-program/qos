package com.paladin.qos.service.school.vo;

import java.util.Date;

public class OrgSchoolNameVO {

	// 
	private String id;

	// 学校全称
	private String schoolFullName;
	
	//父Id
	private String parentId;
	
	//级别
	private String level;
	
	// 
	private Date createTime;

	// 
	private String createUserId;

	// 
	private Date updateTime;

	// 
	private String updateUserId;

	// 
	private Integer isDelete;

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

	public Date updateTime() {
		return updateTime;
	}

	public void updateTime(Date updateTime) {
		this. updateTime = updateTime;
	}

	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
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
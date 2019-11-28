package com.paladin.qos.service.school.dto;

import java.util.Date;

public class OrgSchoolPeopleDTO {

 // 主键
 	private String id;

 	// 学校ID
 	private String schoolId;

 	// 学段
 	private Integer schoolSection;

 	// 年级
 	private Integer grade;
 	
 	private String klass;//班级

 	// 总人数
 	private Integer total;

 	// 寄宿生人数
 	private Integer boarders;

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

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public Integer getSchoolSection() {
		return schoolSection;
	}

	public void setSchoolSection(Integer schoolSection) {
		this.schoolSection = schoolSection;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	
	public Integer getTotal() {
	    return total;
	}

	public void setTotal(Integer total) {
	    this.total = total;
	}

	public Integer getBoarders() {
	    return boarders;
	}

	public void setBoarders(Integer boarders) {
	    this.boarders = boarders;
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

	public String getKlass() {
		return klass;
	}

	public void setKlass(String klass) {
		this.klass = klass;
	}
	
}
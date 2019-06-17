package com.paladin.qos.service.school.vo;

import java.util.Date;

public class OrgSchoolPeopleVO {

	// 主键
	private String id;

	// 学校ID
	private String schoolId;

	// 学段
	private Integer schoolSection;

	// 年级
	private Integer grade;

	// 总人数
	private Integer totalNumber;

	// 寄宿生人数
	private Integer boardersNumber;

	// 教职员工人数
	private Integer teachingNumber;

	// 后勤保障人数
	private Integer logisticsNumber;

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

	public Integer getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
	}

	public Integer getBoardersNumber() {
		return boardersNumber;
	}

	public void setBoardersNumber(Integer boardersNumber) {
		this.boardersNumber = boardersNumber;
	}

	public Integer getTeachingNumber() {
		return teachingNumber;
	}

	public void setTeachingNumber(Integer teachingNumber) {
		this.teachingNumber = teachingNumber;
	}

	public Integer getLogisticsNumber() {
		return logisticsNumber;
	}

	public void setLogisticsNumber(Integer logisticsNumber) {
		this.logisticsNumber = logisticsNumber;
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
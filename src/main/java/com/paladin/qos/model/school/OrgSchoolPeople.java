package com.paladin.qos.model.school;

import com.paladin.framework.common.BaseModel;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class OrgSchoolPeople extends BaseModel {
    
    public static final String COLUMN_SCHOOL_ID= "schoolId";

	// 主键
    	@Id
	@GeneratedValue(generator = "UUID")
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

}
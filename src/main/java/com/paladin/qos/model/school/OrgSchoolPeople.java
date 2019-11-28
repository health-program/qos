package com.paladin.qos.model.school;

import com.paladin.framework.common.BaseModel;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class OrgSchoolPeople extends BaseModel {
    
    public static final String COLUMN_SCHOOL_ID= "schoolId";
    
    public static final String COLUMN_SCHOOL_SECTION= "schoolSection";
    
    public static final String COLUMN_SCHOOL_GRADE= "grade";
    
    public static final String COLUMN_SCHOOL_KLASS= "klass";

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
	
	private String klass;//班级

	// 年级学生人数
	private Integer total;

	// 寄宿生人数
	private Integer boarders;

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

	public String getKlass() {
		return klass;
	}

	public void setKlass(String klass) {
		this.klass = klass;
	}
	
}
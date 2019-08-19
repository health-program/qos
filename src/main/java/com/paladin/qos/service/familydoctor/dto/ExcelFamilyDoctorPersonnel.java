package com.paladin.qos.service.familydoctor.dto;

import com.paladin.framework.excel.read.ReadProperty;

/**   
 * @author MyKite
 * @version 2019年8月14日 下午2:43:18 
 */
public class ExcelFamilyDoctorPersonnel {

    	@ReadProperty(cellIndex = 0)
    	private String teamName;
    	
    	@ReadProperty(cellIndex = 1)
 	private String name;
    	
    	@ReadProperty(cellIndex = 2,enumType="sex-type")
 	private Integer sex;
    	
    	@ReadProperty(cellIndex = 3)
 	private Integer age;
    	
    	@ReadProperty(cellIndex = 4,enumType="oeducation-type")
 	private Integer oeducation;
    	
    	@ReadProperty(cellIndex = 5,enumType="job-rank-type")
 	private Integer jobRank;
    	
    	@ReadProperty(cellIndex = 6,enumType="personnel-category-type")
 	private Integer personnelCategory;
    	
    	@ReadProperty(cellIndex = 7)
 	private String specialtyTeamDoctor;
    	
    	@ReadProperty(cellIndex = 8)
 	private String otherPersonnel;

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

	public Integer getPersonnelCategory() {
	    return personnelCategory;
	}

	public void setPersonnelCategory(Integer personnelCategory) {
	    this.personnelCategory = personnelCategory;
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
}

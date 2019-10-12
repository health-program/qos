package com.paladin.qos.service.school.vo;

public class OrgSchoolEpidemicRateVO {

	private String grouping;//分组（以隶属关系或学校性质来分类）
	
	private String sicknessClassify;//疾病
	
	private String schoolId;//学校id
	
	private String orgSchoolPeopleId;//班级id
	
	private Integer orgSchoolPeopleTotal;//班级人数
	
	private String incidentOrgSchoolPeopleId;//患病班级id
	
	private Integer incidentTotol;//患病人数
	
	private Integer shcoolTotal;//患病学校人数

	public String getGrouping() {
		return grouping;
	}

	public void setGrouping(String grouping) {
		this.grouping = grouping;
	}

	public String getSicknessClassify() {
		return sicknessClassify;
	}

	public void setSicknessClassify(String sicknessClassify) {
		this.sicknessClassify = sicknessClassify;
	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getOrgSchoolPeopleId() {
		return orgSchoolPeopleId;
	}

	public void setOrgSchoolPeopleId(String orgSchoolPeopleId) {
		this.orgSchoolPeopleId = orgSchoolPeopleId;
	}

	public Integer getOrgSchoolPeopleTotal() {
		return orgSchoolPeopleTotal;
	}

	public void setOrgSchoolPeopleTotal(Integer orgSchoolPeopleTotal) {
		this.orgSchoolPeopleTotal = orgSchoolPeopleTotal;
	}

	public String getIncidentOrgSchoolPeopleId() {
		return incidentOrgSchoolPeopleId;
	}

	public void setIncidentOrgSchoolPeopleId(String incidentOrgSchoolPeopleId) {
		this.incidentOrgSchoolPeopleId = incidentOrgSchoolPeopleId;
	}

	public Integer getIncidentTotol() {
		return incidentTotol;
	}

	public void setIncidentTotol(Integer incidentTotol) {
		this.incidentTotol = incidentTotol;
	}

	public Integer getShcoolTotal() {
		return shcoolTotal;
	}

	public void setShcoolTotal(Integer shcoolTotal) {
		this.shcoolTotal = shcoolTotal;
	}
	
	
	
}

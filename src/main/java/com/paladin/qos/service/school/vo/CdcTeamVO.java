package com.paladin.qos.service.school.vo;


public class CdcTeamVO {

	// 
	private String id;

	// 疾控队伍名称
	private String name;

	// 区域
	private Long region;

	// 人均管理面积(KM2)
	private String manageArea;

	// 人均管理学校数
	private Integer manageSchoolAmount;

	// 人均处置疫情量
	private String epidemicAmount;

	// 人居处置预警疫情量
	private String warningEpidemicAmount;

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

	public Long getRegion() {
		return region;
	}

	public void setRegion(Long region) {
		this.region = region;
	}

	public String getManageArea() {
		return manageArea;
	}

	public void setManageArea(String manageArea) {
		this.manageArea = manageArea;
	}

	public Integer getManageSchoolAmount() {
		return manageSchoolAmount;
	}

	public void setManageSchoolAmount(Integer manageSchoolAmount) {
		this.manageSchoolAmount = manageSchoolAmount;
	}

	public String getEpidemicAmount() {
		return epidemicAmount;
	}

	public void setEpidemicAmount(String epidemicAmount) {
		this.epidemicAmount = epidemicAmount;
	}

	public String getWarningEpidemicAmount() {
		return warningEpidemicAmount;
	}

	public void setWarningEpidemicAmount(String warningEpidemicAmount) {
		this.warningEpidemicAmount = warningEpidemicAmount;
	}

}
package com.paladin.qos.service.school.dto;

import java.util.List;


public class OrgSchoolDTO {

	// 
	private String id;

	// 主校ID
	private String parentSchoolId;

	// 地址
	private String address;

	// 隶属关系
	private Integer affiliation;

	// 区域
	private Integer district;

	// 性质
	private Integer nature;

	// 主要领导姓名
	private String mainLeaderName;

	// 主要领导电话
	private String mainLeaderCellphone;

	// 分管领导姓名
	private String chargeLeaderName;

	// 分管领导电话
	private String chargeLeaderCellphone;

	// 具体负责人姓名
	private String specificChargeLeaderName;

	// 具体负责人电话
	private String specificChargeLeaderCellphone;

	private List<OrgSchoolPeopleDTO> people;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentSchoolId() {
		return parentSchoolId;
	}

	public void setParentSchoolId(String parentSchoolId) {
		this.parentSchoolId = parentSchoolId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getAffiliation() {
		return affiliation;
	}

	public void setAffiliation(Integer affiliation) {
		this.affiliation = affiliation;
	}

	public Integer getDistrict() {
		return district;
	}

	public void setDistrict(Integer district) {
		this.district = district;
	}

	public Integer getNature() {
		return nature;
	}

	public void setNature(Integer nature) {
		this.nature = nature;
	}

	public String getMainLeaderName() {
		return mainLeaderName;
	}

	public void setMainLeaderName(String mainLeaderName) {
		this.mainLeaderName = mainLeaderName;
	}

	public String getMainLeaderCellphone() {
		return mainLeaderCellphone;
	}

	public void setMainLeaderCellphone(String mainLeaderCellphone) {
		this.mainLeaderCellphone = mainLeaderCellphone;
	}

	public String getChargeLeaderName() {
		return chargeLeaderName;
	}

	public void setChargeLeaderName(String chargeLeaderName) {
		this.chargeLeaderName = chargeLeaderName;
	}

	public String getChargeLeaderCellphone() {
		return chargeLeaderCellphone;
	}

	public void setChargeLeaderCellphone(String chargeLeaderCellphone) {
		this.chargeLeaderCellphone = chargeLeaderCellphone;
	}

	public String getSpecificChargeLeaderName() {
		return specificChargeLeaderName;
	}

	public void setSpecificChargeLeaderName(String specificChargeLeaderName) {
		this.specificChargeLeaderName = specificChargeLeaderName;
	}

	public String getSpecificChargeLeaderCellphone() {
		return specificChargeLeaderCellphone;
	}

	public void setSpecificChargeLeaderCellphone(String specificChargeLeaderCellphone) {
		this.specificChargeLeaderCellphone = specificChargeLeaderCellphone;
	}

	public List<OrgSchoolPeopleDTO> getPeople() {
	    return people;
	}

	public void setPeople(List<OrgSchoolPeopleDTO> people) {
	    this.people = people;
	}
}
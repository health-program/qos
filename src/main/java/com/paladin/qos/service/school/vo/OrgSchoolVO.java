package com.paladin.qos.service.school.vo;

import java.util.Date;
import java.util.List;

import com.paladin.qos.model.school.OrgSchoolPeople;

public class OrgSchoolVO {

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
	
	private List<OrgSchoolPeople> people;

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

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public List<OrgSchoolPeople> getPeople() {
	    return people;
	}

	public void setPeople(List<OrgSchoolPeople> people) {
	    this.people = people;
	}

}
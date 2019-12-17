package com.paladin.qos.model.gongwei;

public class EntityGongweiYearReport {

	private String unitId;

	private String unitName;

	private String year;

	private Integer addNumber;

	private Integer mangerNumber;

	private Integer followNumber;

	private Integer  recentNumber;

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Integer getAddNumber() {
		return addNumber;
	}

	public void setAddNumber(Integer addNumber) {
		this.addNumber = addNumber;
	}

	public Integer getMangerNumber() {
		return mangerNumber;
	}

	public void setMangerNumber(Integer mangerNumber) {
		this.mangerNumber = mangerNumber;
	}

	public Integer getFollowNumber() {
		return followNumber;
	}

	public void setFollowNumber(Integer followNumber) {
		this.followNumber = followNumber;
	}

	public Integer getRecentNumber() {
		return recentNumber;
	}

	public void setRecentNumber(Integer recentNumber) {
		this.recentNumber = recentNumber;
	}
}

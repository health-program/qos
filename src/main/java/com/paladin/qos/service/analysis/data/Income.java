package com.paladin.qos.service.analysis.data;

import com.paladin.qos.analysis.DataConstantContainer;

public class Income {
	
	private String unitId;
    private int year;
    private int month;
	private long eventNum;
	private long totalNum;
	
	private double totalMoney;
	private double medicalMoney;
	private double drugMoney;
	private double otherMoney;
	
	public String getUnitName() {
		return DataConstantContainer.getUnitName(unitId);
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public long getEventNum() {
		return eventNum;
	}

	public void setEventNum(long eventNum) {
		this.eventNum = eventNum;
	}

	public long getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(long totalNum) {
		this.totalNum = totalNum;
	}

	public double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public double getMedicalMoney() {
		return medicalMoney;
	}

	public void setMedicalMoney(double medicalMoney) {
		this.medicalMoney = medicalMoney;
	}

	public double getDrugMoney() {
		return drugMoney;
	}

	public void setDrugMoney(double drugMoney) {
		this.drugMoney = drugMoney;
	}

	public double getOtherMoney() {
		return otherMoney;
	}

	public void setOtherMoney(double otherMoney) {
		this.otherMoney = otherMoney;
	}
	
}

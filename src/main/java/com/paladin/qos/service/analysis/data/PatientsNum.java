package com.paladin.qos.service.analysis.data;

import com.paladin.qos.analysis.DataConstantContainer;

public class PatientsNum {

	
    private String unitId;
    private int year;
    private int month;
	private long eventNum;
	private long totalNum;
	
	private long outpatientNumber;//门诊人次数
	private long emergencyNumber;//急诊人次数
	private long patientsNumber;//门急诊总人数
	private long visitDoctorNumber;//出诊医生数
	private long averageNumber;//医生平均门急诊量
	private long recoveryNumber;//康复服务数
	
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

	public long getOutpatientNumber() {
		return outpatientNumber;
	}

	public void setOutpatientNumber(long outpatientNumber) {
		this.outpatientNumber = outpatientNumber;
	}

	public long getEmergencyNumber() {
		return emergencyNumber;
	}

	public void setEmergencyNumber(long emergencyNumber) {
		this.emergencyNumber = emergencyNumber;
	}

	public long getPatientsNumber() {
		return patientsNumber;
	}

	public void setPatientsNumber(long patientsNumber) {
		this.patientsNumber = patientsNumber;
	}

	public long getVisitDoctorNumber() {
		return visitDoctorNumber;
	}

	public void setVisitDoctorNumber(long visitDoctorNumber) {
		this.visitDoctorNumber = visitDoctorNumber;
	}

	public long getAverageNumber() {
		return averageNumber;
	}

	public void setAverageNumber(long averageNumber) {
		this.averageNumber = averageNumber;
	}

	public long getRecoveryNumber() {
		return recoveryNumber;
	}

	public void setRecoveryNumber(long recoveryNumber) {
		this.recoveryNumber = recoveryNumber;
	}
	
	
}

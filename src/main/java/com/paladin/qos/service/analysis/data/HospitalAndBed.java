package com.paladin.qos.service.analysis.data;

import com.paladin.qos.analysis.DataConstantContainer;

public class HospitalAndBed {


    private String unitId;
    private int year;
    private int month;
	private long eventNum;
	private long totalNum;
	
    private long inHospitalNum;//住院人次数
    private long outHospitalNum;//出院人次数
    private long onHospitalNum;//在院人数
    private long ratedBedNum;//额定床位
    private long bedInUsedNum;//使用床位
    private long bedInUsedRate;//病床使用率
	
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

	public long getInHospitalNum() {
		return inHospitalNum;
	}

	public void setInHospitalNum(long inHospitalNum) {
		this.inHospitalNum = inHospitalNum;
	}

	public long getOutHospitalNum() {
		return outHospitalNum;
	}

	public void setOutHospitalNum(long outHospitalNum) {
		this.outHospitalNum = outHospitalNum;
	}

	public long getOnHospitalNum() {
		return onHospitalNum;
	}

	public void setOnHospitalNum(long onHospitalNum) {
		this.onHospitalNum = onHospitalNum;
	}

	public long getRatedBedNum() {
		return ratedBedNum;
	}

	public void setRatedBedNum(long ratedBedNum) {
		this.ratedBedNum = ratedBedNum;
	}

	public long getBedInUsedNum() {
		return bedInUsedNum;
	}

	public void setBedInUsedNum(long bedInUsedNum) {
		this.bedInUsedNum = bedInUsedNum;
	}

	public long getBedInUsedRate() {
		return bedInUsedRate;
	}

	public void setBedInUsedRate(long bedInUsedRate) {
		this.bedInUsedRate = bedInUsedRate;
	}
	
	
}

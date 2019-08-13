package com.paladin.qos.service.data.dto;


public class DataProcessedYearDTO {

	// 
	private String id;

	// 事件ID
	private String eventId;

	// 年份
	private Integer year;

	// 机构ID
	private String unitId;

	// 样本总数
	private Integer totalNum;

	// 事件总数
	private Integer eventNum;

	// 
	private String rate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public Integer getEventNum() {
		return eventNum;
	}

	public void setEventNum(Integer eventNum) {
		this.eventNum = eventNum;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

}
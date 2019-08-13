package com.paladin.qos.model.data;

import javax.persistence.Id;

public class DataProcessedDay {

	// ID
	@Id
	private String id;

	// 事件ID
	private String eventId;

	// 年份
	private Integer year;

	// 月份
	private Integer month;

	// 日期
	private Integer day;

	// 按年第几个星期
	private Integer weekYear;

	// 按月第几个星期
	private Integer weekMonth;

	// 机构单位ID
	private String unitId;

	// 样本总数
	private Integer totalNum;

	// 事件总数
	private Integer eventNum;

	// 比率
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

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Integer getWeekYear() {
		return weekYear;
	}

	public void setWeekYear(Integer weekYear) {
		this.weekYear = weekYear;
	}

	public Integer getWeekMonth() {
		return weekMonth;
	}

	public void setWeekMonth(Integer weekMonth) {
		this.weekMonth = weekMonth;
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
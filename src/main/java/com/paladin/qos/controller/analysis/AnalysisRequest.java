package com.paladin.qos.controller.analysis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AnalysisRequest {

	public static Date default_start_time;

	static {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			default_start_time = format.parse("2019-01-01");
		} catch (ParseException e) {
		}
	}

	private Date startTime = default_start_time;
	private Date endTime = new Date();
	private String unitId;
	private String eventId;

	private List<String> unitIds;
	private List<String> eventIds;

	private int dataType;

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime == null ? default_start_time : startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime == null ? new Date() : endTime;
	}

	public List<String> getUnitIds() {
		return unitIds;
	}

	public void setUnitIds(List<String> unitIds) {
		this.unitIds = unitIds;
	}

	public int getDataType() {
		return dataType;
	}

	public void setDataType(int dataType) {
		this.dataType = dataType;
	}

	public List<String> getEventIds() {
		return eventIds;
	}

	public void setEventIds(List<String> eventIds) {
		this.eventIds = eventIds;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

}

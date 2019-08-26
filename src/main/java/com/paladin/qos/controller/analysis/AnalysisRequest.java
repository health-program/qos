package com.paladin.qos.controller.analysis;

import java.util.Date;
import java.util.List;

public class AnalysisRequest {
	
	private Date startTime;
	private Date endTime;
	private List<String> unitIds;
	private String eventId;
	
	private List<String> eventIds;
	
	private int dataType;
	
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public List<String> getUnitIds() {
		return unitIds;
	}
	public void setUnitIds(List<String> unitIds) {
		this.unitIds = unitIds;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
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
	
	
}

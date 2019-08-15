package com.paladin.qos.controller.analysis;

import java.util.Date;
import java.util.List;

public class DataProcessRequest {
	
	private Date startTime;
	private Date endTime;
	private List<String> unitIds;
	private String eventId;
	
	
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
	
	
}

package com.paladin.qos.service.analysis.data;

import java.util.List;

public class ValidateResult {
	
	private String eventId;
	private String unitId;
	private int firstDay;
	private int lastDay;
	
	private List<Integer> lostDays;

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public int getFirstDay() {
		return firstDay;
	}

	public void setFirstDay(int firstDay) {
		this.firstDay = firstDay;
	}

	public int getLastDay() {
		return lastDay;
	}

	public void setLastDay(int lastDay) {
		this.lastDay = lastDay;
	}

	public List<Integer> getLostDays() {
		return lostDays;
	}

	public void setLostDays(List<Integer> lostDays) {
		this.lostDays = lostDays;
	}
	
	
}

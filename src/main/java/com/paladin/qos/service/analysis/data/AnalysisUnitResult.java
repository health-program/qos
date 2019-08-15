package com.paladin.qos.service.analysis.data;

import java.util.List;

import com.paladin.qos.analysis.DataConstantContainer;

public class AnalysisUnitResult {
	
	private String eventId;
	private List<AnalysisUnit> points;
	
	public AnalysisUnitResult(String eventId,  List<AnalysisUnit> points) {
		this.eventId = eventId;
		this.points = points;
	}

	public String getEventName() {
		return DataConstantContainer.getEventName(eventId);
	}
	
	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public List<AnalysisUnit> getPoints() {
		return points;
	}

	public void setPoints(List<AnalysisUnit> points) {
		this.points = points;
	}
	
	
	
}

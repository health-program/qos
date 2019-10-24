package com.paladin.qos.controller.analysis;

import java.util.Date;
import java.util.List;

import com.paladin.qos.analysis.DataConstantContainer;

public class AnalysisRequest {

	private Date startTime = DataConstantContainer.DEFAULT_START_TIME;
	private Date endTime = new Date();
	private String unitId;
	private String eventId;
	private String dataId;
	private List<String> unitIds;
	private List<String> eventIds;

	private int dataType;
	private int byUnit = 1;
	
	
	private List<String> ignoreUnitIds;

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime == null ? DataConstantContainer.DEFAULT_START_TIME : startTime;
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

	public int getByUnit() {
		return byUnit;
	}

	public void setByUnit(int byUnit) {
		this.byUnit = byUnit;
	}

	public List<String> getIgnoreUnitIds() {
		return ignoreUnitIds;
	}

	public void setIgnoreUnitIds(List<String> ignoreUnitIds) {
		this.ignoreUnitIds = ignoreUnitIds;
	}

}

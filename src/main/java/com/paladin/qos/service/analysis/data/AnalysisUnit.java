package com.paladin.qos.service.analysis.data;

import com.paladin.qos.analysis.DataConstantContainer;

public class AnalysisUnit {
	
	private String unitId;
	
	private long eventNum;
	private long totalNum;
	
	public String getUnitName() {
		return DataConstantContainer.getUnitName(unitId);
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
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

}

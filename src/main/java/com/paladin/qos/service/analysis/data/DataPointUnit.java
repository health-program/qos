package com.paladin.qos.service.analysis.data;

import java.util.List;

import com.paladin.qos.analysis.DataConstantContainer;

public class DataPointUnit<T> {

	private String unitId;
	private List<T> points;

	public DataPointUnit(String unitId, List<T> points) {
		this.unitId = unitId;
		this.points = points;
	}
	
	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public List<T> getPoints() {
		return points;
	}

	public void setPoints(List<T> points) {
		this.points = points;
	}

	public String getUnitName() {
		return DataConstantContainer.getUnitName(unitId);
	}

}

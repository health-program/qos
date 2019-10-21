package com.paladin.qos.service.org.dto;

import com.paladin.framework.common.OffsetPage;
import com.paladin.framework.common.QueryCondition;
import com.paladin.framework.common.QueryType;

import java.util.List;

public class OrgPersonQuery extends OffsetPage {

	private String unitId;
	private Integer year;

	@QueryCondition(type = QueryType.EQUAL)
	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	@QueryCondition(type = QueryType.EQUAL)
	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
}
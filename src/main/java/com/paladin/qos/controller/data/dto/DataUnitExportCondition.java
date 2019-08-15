package com.paladin.qos.controller.data.dto;

import com.paladin.common.core.export.ExportCondition;
import com.paladin.qos.service.data.dto.DataUnitQuery;

public class DataUnitExportCondition extends ExportCondition {

	private DataUnitQuery query;

	public DataUnitQuery getQuery() {
		return query;
	}

	public void setQuery(DataUnitQuery query) {
		this.query = query;
	}

}
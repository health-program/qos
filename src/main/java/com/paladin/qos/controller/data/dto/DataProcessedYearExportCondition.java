package com.paladin.qos.controller.data.dto;

import com.paladin.common.core.export.ExportCondition;
import com.paladin.qos.service.data.dto.DataProcessedYearQuery;

public class DataProcessedYearExportCondition extends ExportCondition {

	private DataProcessedYearQuery query;

	public DataProcessedYearQuery getQuery() {
		return query;
	}

	public void setQuery(DataProcessedYearQuery query) {
		this.query = query;
	}

}
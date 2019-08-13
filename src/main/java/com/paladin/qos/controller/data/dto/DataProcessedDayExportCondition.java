package com.paladin.qos.controller.data.dto;

import com.paladin.common.core.export.ExportCondition;
import com.paladin.qos.service.data.dto.DataProcessedDayQuery;

public class DataProcessedDayExportCondition extends ExportCondition {

	private DataProcessedDayQuery query;

	public DataProcessedDayQuery getQuery() {
		return query;
	}

	public void setQuery(DataProcessedDayQuery query) {
		this.query = query;
	}

}
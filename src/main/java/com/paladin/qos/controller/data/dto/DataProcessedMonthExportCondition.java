package com.paladin.qos.controller.data.dto;

import com.paladin.common.core.export.ExportCondition;
import com.paladin.qos.service.data.dto.DataProcessedMonthQuery;

public class DataProcessedMonthExportCondition extends ExportCondition {

	private DataProcessedMonthQuery query;

	public DataProcessedMonthQuery getQuery() {
		return query;
	}

	public void setQuery(DataProcessedMonthQuery query) {
		this.query = query;
	}

}
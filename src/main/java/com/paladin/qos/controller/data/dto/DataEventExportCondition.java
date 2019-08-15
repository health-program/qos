package com.paladin.qos.controller.data.dto;

import com.paladin.common.core.export.ExportCondition;
import com.paladin.qos.service.data.dto.DataEventQuery;

public class DataEventExportCondition extends ExportCondition {

	private DataEventQuery query;

	public DataEventQuery getQuery() {
		return query;
	}

	public void setQuery(DataEventQuery query) {
		this.query = query;
	}

}
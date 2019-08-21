package com.paladin.qos.controller.infectioncomplication.dto;

import com.paladin.common.core.export.ExportCondition;
import com.paladin.qos.service.infectioncomplication.dto.InfectionQuery;

public class InfectionExportCondition extends ExportCondition {

	private InfectionQuery query;

	public InfectionQuery getQuery() {
		return query;
	}

	public void setQuery(InfectionQuery query) {
		this.query = query;
	}

}
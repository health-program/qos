package com.paladin.qos.controller.indication.dto;

import com.paladin.common.core.export.ExportCondition;
import com.paladin.qos.service.infectIndication.dto.InfectIndicationQuery;

public class InfectIndicationExportCondition extends ExportCondition {

	private InfectIndicationQuery query;

	public InfectIndicationQuery getQuery() {
		return query;
	}

	public void setQuery(InfectIndicationQuery query) {
		this.query = query;
	}

}
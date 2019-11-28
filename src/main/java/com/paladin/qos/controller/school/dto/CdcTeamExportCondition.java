package com.paladin.qos.controller.school.dto;

import com.paladin.common.core.export.ExportCondition;
import com.paladin.qos.service.school.dto.CdcTeamQuery;

public class CdcTeamExportCondition extends ExportCondition {

	private CdcTeamQuery query;

	public CdcTeamQuery getQuery() {
		return query;
	}

	public void setQuery(CdcTeamQuery query) {
		this.query = query;
	}

}
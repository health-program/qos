package com.paladin.qos.controller.improvement.dto;

import com.paladin.common.core.export.ExportCondition;
import com.paladin.qos.service.improvement.dto.ImprovementProblemQuery;

public class ImprovementProblemExportCondition extends ExportCondition {

	private ImprovementProblemQuery query;

	public ImprovementProblemQuery getQuery() {
		return query;
	}

	public void setQuery(ImprovementProblemQuery query) {
		this.query = query;
	}

}
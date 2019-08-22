package com.paladin.qos.controller.countAntibiotics.dto;

import com.paladin.common.core.export.ExportCondition;
import com.paladin.qos.service.countantibiotics.dto.CountAntibioticsQuery;

public class CountAntibioticsExportCondition  extends ExportCondition {
    private CountAntibioticsQuery query;

    public CountAntibioticsQuery getQuery() {
		return query;
	}

	public void setQuery(CountAntibioticsQuery query) {
		this.query = query;
	}
}

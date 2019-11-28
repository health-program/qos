package com.paladin.qos.controller.count.dto;

import com.paladin.common.core.export.ExportCondition;
import com.paladin.qos.service.count.dto.CountReferralQuery;

public class CountReferralExportCondition extends ExportCondition {

	private CountReferralQuery query;

	public CountReferralQuery getQuery() {
		return query;
	}

	public void setQuery(CountReferralQuery query) {
		this.query = query;
	}
}
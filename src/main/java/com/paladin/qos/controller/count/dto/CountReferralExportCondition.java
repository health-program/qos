package com.paladin.qos.controller.count.dto;

import com.paladin.common.core.export.ExportCondition;
import com.paladin.qos.service.count.dto.CountReferralQuery;

public class CountReferralExportCondition extends ExportCondition {

	private CountReferralQuery countReferralQuery;

	public CountReferralQuery getCountReferralQuery() {
		return countReferralQuery;
	}

	public void setCountReferralQuery(CountReferralQuery countReferralQuery) {
		this.countReferralQuery = countReferralQuery;
	}
}
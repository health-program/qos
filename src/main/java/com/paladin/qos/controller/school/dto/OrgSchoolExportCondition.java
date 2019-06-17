package com.paladin.qos.controller.school.dto;

import com.paladin.common.core.export.ExportCondition;
import com.paladin.qos.service.school.dto.OrgSchoolQuery;

public class OrgSchoolExportCondition extends ExportCondition {

	private OrgSchoolQuery query;

	public OrgSchoolQuery getQuery() {
		return query;
	}

	public void setQuery(OrgSchoolQuery query) {
		this.query = query;
	}

}
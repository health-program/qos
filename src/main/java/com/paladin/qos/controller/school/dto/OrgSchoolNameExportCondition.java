package com.paladin.qos.controller.school.dto;

import com.paladin.common.core.export.ExportCondition;
import com.paladin.qos.service.school.dto.OrgSchoolNameQuery;

public class OrgSchoolNameExportCondition extends ExportCondition {

	private OrgSchoolNameQuery query;

	public OrgSchoolNameQuery getQuery() {
		return query;
	}

	public void setQuery(OrgSchoolNameQuery query) {
		this.query = query;
	}

}
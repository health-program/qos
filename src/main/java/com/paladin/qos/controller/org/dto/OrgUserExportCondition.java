package com.paladin.qos.controller.org.dto;

import com.paladin.common.core.export.ExportCondition;
import com.paladin.qos.service.org.dto.OrgUserQuery;

public class OrgUserExportCondition extends ExportCondition {

	private OrgUserQuery query;

	public OrgUserQuery getQuery() {
		return query;
	}

	public void setQuery(OrgUserQuery query) {
		this.query = query;
	}

}
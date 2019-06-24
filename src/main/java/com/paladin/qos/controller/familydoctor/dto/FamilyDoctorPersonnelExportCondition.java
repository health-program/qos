package com.paladin.qos.controller.familydoctor.dto;

import com.paladin.common.core.export.ExportCondition;
import com.paladin.qos.service.familydoctor.dto.FamilyDoctorPersonnelQuery;

public class FamilyDoctorPersonnelExportCondition extends ExportCondition {

	private FamilyDoctorPersonnelQuery query;

	public FamilyDoctorPersonnelQuery getQuery() {
		return query;
	}

	public void setQuery(FamilyDoctorPersonnelQuery query) {
		this.query = query;
	}

}
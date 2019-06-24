package com.paladin.qos.controller.familydoctor.dto;

import com.paladin.common.core.export.ExportCondition;
import com.paladin.qos.service.familydoctor.dto.FamilyDoctorUnitQuery;

public class FamilyDoctorUnitExportCondition extends ExportCondition {

	private FamilyDoctorUnitQuery query;

	public FamilyDoctorUnitQuery getQuery() {
		return query;
	}

	public void setQuery(FamilyDoctorUnitQuery query) {
		this.query = query;
	}

}
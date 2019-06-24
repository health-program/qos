package com.paladin.qos.controller.familydoctor.dto;

import com.paladin.common.core.export.ExportCondition;
import com.paladin.qos.service.familydoctor.dto.FamilyDoctorTeamQuery;

public class FamilyDoctorTeamExportCondition extends ExportCondition {

	private FamilyDoctorTeamQuery query;

	public FamilyDoctorTeamQuery getQuery() {
		return query;
	}

	public void setQuery(FamilyDoctorTeamQuery query) {
		this.query = query;
	}

}
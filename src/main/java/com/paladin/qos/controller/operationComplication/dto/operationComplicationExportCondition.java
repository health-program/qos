package com.paladin.qos.controller.operationComplication.dto;

import com.paladin.common.core.export.ExportCondition;
import com.paladin.qos.service.operationComplication.dto.OperationComplicationQueryDTO;

public class operationComplicationExportCondition  extends ExportCondition  {

	private  OperationComplicationQueryDTO  query;

	public OperationComplicationQueryDTO getQuery() {
		return query;
	}

	public void setQuery(OperationComplicationQueryDTO query) {
		this.query = query;
	}

	 
	 
	 
}

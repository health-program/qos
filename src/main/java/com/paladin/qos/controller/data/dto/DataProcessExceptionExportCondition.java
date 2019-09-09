package com.paladin.qos.controller.data.dto;

import com.paladin.common.core.export.ExportCondition;
import com.paladin.qos.service.data.dto.DataProcessExceptionQuery;

public class DataProcessExceptionExportCondition extends ExportCondition {

	private DataProcessExceptionQuery query;

	public DataProcessExceptionQuery getQuery() {
		return query;
	}

	public void setQuery(DataProcessExceptionQuery query) {
		this.query = query;
	}

}
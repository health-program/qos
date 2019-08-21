package com.paladin.qos.controller.analysis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.analysis.DataConstantContainer;
import com.paladin.qos.analysis.DataConstantContainer.Unit;
import com.paladin.qos.analysis.DataProcessContainer;
import com.paladin.qos.analysis.TimeUtil;
import com.paladin.qos.service.analysis.AnalysisService;
import com.paladin.qos.service.analysis.data.DataResult;

@Controller
@RequestMapping("/qos/analysis")
public class AnalysisController {

	@Autowired
	private DataProcessContainer dataProcessContainer;

	@Autowired
	private AnalysisService analysisService;

	@GetMapping("/process/index")
	public Object processIndex() {
		return "/qos/analysis/index_process";
	}

	@PostMapping("/process")
	@ResponseBody
	public Object process(AnalysisRequest request) {

		List<Unit> units = DataConstantContainer.getUnitList();
		List<String> unitIds = new ArrayList<>();
		for (Unit unit : units) {
			unitIds.add(unit.getId());
		}

		dataProcessContainer.processData(request.getStartTime(), request.getEndTime(), unitIds, request.getEventId());
		return CommonResponse.getSuccessResponse();
	}

	@GetMapping("/data/index")
	public Object dataIndex() {
		return "/qos/analysis/index_data";
	}

	@PostMapping("/data/processed")
	@ResponseBody
	public Object getProcessData(AnalysisRequest request) {
		int dataType = request.getDataType();
		String eventId = request.getEventId();
		List<String> unitIds = request.getUnitIds();
		Date startDate = request.getStartTime();
		Date endDate = request.getEndTime();

		if (startDate == null || eventId == null || eventId.length() == 0) {
			return CommonResponse.getFailResponse();
		}
		
		if(endDate == null) {
			endDate = new Date();
		}
		
		@SuppressWarnings("rawtypes")
		DataResult result = null;

		if (dataType == AnalysisService.DATA_TYPE_DAY) {
			result = analysisService.getDataOfDay(eventId, unitIds, startDate, endDate);
		} else if (dataType == AnalysisService.DATA_TYPE_MONTH) {
			result = analysisService.getDataOfMonth(eventId, unitIds, startDate, endDate);
		} else if (dataType == AnalysisService.DATA_TYPE_YEAR) {
			result = analysisService.getDataOfYear(eventId, unitIds, TimeUtil.getYear(startDate), TimeUtil.getYear(endDate));
		} else if (dataType == AnalysisService.DATA_TYPE_WEEK_MONTH) {
			result = analysisService.getDataOfWeekYear(eventId, unitIds, TimeUtil.getYear(startDate), TimeUtil.getYear(endDate));
		} else if (dataType == AnalysisService.DATA_TYPE_WEEK_YEAR) {
			result = analysisService.getDataOfWeekMonth(eventId, unitIds, startDate, endDate);
		} else {
			return CommonResponse.getFailResponse();
		}

		return CommonResponse.getSuccessResponse(result);
	}

	@RequestMapping(value = "/data/processing", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object getAnaysisData(AnalysisRequest request) {
		String eventId = request.getEventId();
		Date startDate = request.getStartTime();
		Date endDate = request.getEndTime();

		if (startDate == null || eventId == null || eventId.length() == 0) {
			return CommonResponse.getFailResponse();
		}

		if (endDate == null) {
			endDate = new Date();
		}

		return CommonResponse.getSuccessResponse(analysisService.getAnalysisResultByUnit(eventId, startDate, endDate));
	}
}

package com.paladin.qos.controller.analysis;

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
import com.paladin.qos.analysis.DataProcessManager;
import com.paladin.qos.analysis.TimeUtil;
import com.paladin.qos.service.analysis.AnalysisService;
import com.paladin.qos.service.analysis.data.DataResult;

@Controller
@RequestMapping("/qos/analysis")
public class AnalysisController {

	@Autowired
	private DataProcessManager dataProcessManager;

	@Autowired
	private AnalysisService analysisService;

	@GetMapping("/process/index")
	public Object processIndex() {
		return "/qos/analysis/process_index";
	}

	@GetMapping("/processed/index")
	public Object dataIndex() {
		return "/qos/analysis/processed_index";
	}

	@PostMapping("/data/process")
	@ResponseBody
	public Object processData(AnalysisRequest request) {
		List<String> eventIds = request.getEventIds();
		List<String> unitIds = request.getUnitIds();
		Date startDate = request.getStartTime();
		Date endDate = request.getEndTime();

		if (endDate == null) {
			endDate = new Date();
		}

		if (dataProcessManager.processDataByThread(startDate, endDate, unitIds, eventIds)) {
			return CommonResponse.getSuccessResponse();
		} else {
			return CommonResponse.getFailResponse("有正在处理中的数据");
		}
	}

	@GetMapping("/data/process/status")
	@ResponseBody
	public Object getProcessDataStatus() {
		return CommonResponse.getSuccessResponse(dataProcessManager.getProcessDataStatus());
	}

	@PostMapping("/data/process/day")
	@ResponseBody
	public Object processDataOneDay(AnalysisRequest request) {
		List<String> eventIds = request.getEventIds();
		List<String> unitIds = request.getUnitIds();
		Date startDate = request.getStartTime();
		startDate = TimeUtil.toDayTime(startDate);
		Date endDate = new Date(startDate.getTime() + TimeUtil.MILLIS_IN_DAY);

		dataProcessManager.processData(startDate, endDate, unitIds, eventIds);
		return CommonResponse.getSuccessResponse();
	}

	@PostMapping("/data/validate")
	@ResponseBody
	public Object validateProcessedData(AnalysisRequest request) {
		String eventId = request.getEventId();
		String unitId = request.getUnitId();
		return CommonResponse.getSuccessResponse(analysisService.validateProcessedData(eventId, unitId));
	}

	@PostMapping("/data/instalments")
	@ResponseBody
	public Object getProcessedDataByInstalments(AnalysisRequest request) {
		int dataType = request.getDataType();
		String eventId = request.getEventId();
		List<String> unitIds = request.getUnitIds();
		Date startDate = request.getStartTime();
		Date endDate = request.getEndTime();

		if (startDate == null || eventId == null || eventId.length() == 0) {
			return CommonResponse.getFailResponse();
		}

		if (endDate == null) {
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

	@RequestMapping(value = "/data/once", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object getProcessedDataByOnce(AnalysisRequest request) {
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

	@RequestMapping(value = "/processed/count", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object getProcessedDataByCount(AnalysisRequest request) {
		String eventId = request.getEventId();
		Date startDate = request.getStartTime();
		Date endDate = request.getEndTime();

		if (startDate == null || eventId == null || eventId.length() == 0) {
			return CommonResponse.getFailResponse();
		}

		if (endDate == null) {
			endDate = new Date();
		}

		return CommonResponse.getSuccessResponse(analysisService.countTotalNumByUnit(eventId, startDate, endDate));
	}

}

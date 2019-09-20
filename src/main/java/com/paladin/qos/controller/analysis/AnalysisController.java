package com.paladin.qos.controller.analysis;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.analysis.DataConstantContainer;
import com.paladin.qos.analysis.DataConstantContainer.Event;
import com.paladin.qos.analysis.DataConstantContainer.Unit;
import com.paladin.qos.analysis.DataProcessManager;
import com.paladin.qos.analysis.TimeUtil;
import com.paladin.qos.model.data.DataEvent;
import com.paladin.qos.service.analysis.AnalysisService;
import com.paladin.qos.service.analysis.data.DataPointDay;
import com.paladin.qos.service.analysis.data.DataPointMonth;
import com.paladin.qos.service.analysis.data.DataPointYear;
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

		if (unitIds == null || unitIds.size() == 0) {
			List<Unit> units = DataConstantContainer.getUnitList();
			unitIds = new ArrayList<>();
			for (Unit unit : units) {
				unitIds.add(unit.getId());
			}
		}

		if (unitIds == null || unitIds.size() == 0) {
			List<Unit> units = DataConstantContainer.getUnitList();
			unitIds = new ArrayList<>();
			for (Unit unit : units) {
				unitIds.add(unit.getId());
			}
		}

		if (dataProcessManager.processDataByThread(startDate, endDate, unitIds, eventIds)) {
			return CommonResponse.getSuccessResponse();
		} else {
			return CommonResponse.getFailResponse("有正在处理中的数据");
		}
	}

	@PostMapping("/data/process/schedule")
	@ResponseBody
	public Object processDataSchedule() {
		dataProcessManager.processSchedule();
		return CommonResponse.getSuccessResponse();
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
		Date startDate = request.getStartTime();
		Date endDate = request.getEndTime();
		return CommonResponse.getSuccessResponse(analysisService.validateProcessedData(startDate, endDate));
	}

	@PostMapping("/data/test")
	@ResponseBody
	public Object testProcessor() {
		return CommonResponse.getSuccessResponse(analysisService.testProcessors());
	}

	@PostMapping("/data/get/day/instalments")
	@ResponseBody
	public Object getDataOfDayByInstalments(AnalysisRequest request) {
		List<String> eventIds = request.getEventIds();
		Date startDate = request.getStartTime();
		Date endDate = request.getEndTime();

		if (eventIds != null && eventIds.size() > 0) {
			Map<String, DataResult<DataPointDay>> map = new HashMap<>();
			for (String eventId : eventIds) {
				DataResult<DataPointDay> item = analysisService.getDataSetOfDay(eventId, startDate, endDate);
				if (item != null) {
					map.put(eventId, item);
				}
			}
			return CommonResponse.getSuccessResponse(map);
		} else {
			String eventId = request.getEventId();
			return CommonResponse.getSuccessResponse(analysisService.getDataSetOfDay(eventId, startDate, endDate));
		}
	}

	@PostMapping("/data/get/month/instalments")
	@ResponseBody
	public Object getDataOfMonthByInstalments(AnalysisRequest request) {
		List<String> eventIds = request.getEventIds();
		Date startDate = request.getStartTime();
		Date endDate = request.getEndTime();

		if (eventIds != null && eventIds.size() > 0) {
			Map<String, DataResult<DataPointMonth>> map = new HashMap<>();
			for (String eventId : eventIds) {
				DataResult<DataPointMonth> item = analysisService.getDataSetOfMonth(eventId, startDate, endDate);
				if (item != null) {
					map.put(eventId, item);
				}
			}
			return CommonResponse.getSuccessResponse(map);
		} else {
			String eventId = request.getEventId();
			return CommonResponse.getSuccessResponse(analysisService.getDataSetOfMonth(eventId, startDate, endDate));
		}
	}

	@PostMapping("/data/get/year/instalments")
	@ResponseBody
	public Object getDataOfYearByInstalments(AnalysisRequest request) {
		Date startDate = request.getStartTime();
		Date endDate = request.getEndTime();
		int startYear = TimeUtil.getYear(startDate);
		int endYear = TimeUtil.getYear(endDate);

		List<String> eventIds = request.getEventIds();
		if (eventIds != null && eventIds.size() > 0) {
			Map<String, DataResult<DataPointYear>> map = new HashMap<>();
			for (String eventId : eventIds) {
				DataResult<DataPointYear> item = analysisService.getDataSetOfYear(eventId, startYear, endYear);
				if (item != null) {
					map.put(eventId, item);
				}
			}
			return CommonResponse.getSuccessResponse(map);
		} else {
			String eventId = request.getEventId();
			return CommonResponse.getSuccessResponse(analysisService.getDataSetOfYear(eventId, startYear, endYear));
		}
	}

	@RequestMapping(value = "/data/get/unit", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object getProcessedDataByCount(AnalysisRequest request) {
		Date startDate = request.getStartTime();
		Date endDate = request.getEndTime();

		List<String> eventIds = request.getEventIds();
		if (eventIds != null && eventIds.size() > 0) {
			Map<String, Object> map = new HashMap<>();
			for (String eventId : eventIds) {
				Event event = DataConstantContainer.getEvent(eventId);
				if (event != null) {
					int eventType = event.getEventType();
					int unitType = getUnitType(event);
					if (DataEvent.EVENT_TYPE_COUNT == eventType) {
						Object item = analysisService.countTotalNumByUnit(eventId, unitType, startDate, endDate);
						if (item != null) {
							map.put(eventId, item);
						}
					} else if (DataEvent.EVENT_TYPE_RATE == eventType) {
						Object item = analysisService.getAnalysisResultByUnit(eventId, unitType, startDate, endDate);
						if (item != null) {
							map.put(eventId, item);
						}
					}
				}
			}
			return CommonResponse.getSuccessResponse(map);
		} else {
			String eventId = request.getEventId();
			Event event = DataConstantContainer.getEvent(eventId);
			if (event != null) {
				int eventType = event.getEventType();
				int unitType = getUnitType(event);
				if (DataEvent.EVENT_TYPE_COUNT == eventType) {
					return CommonResponse.getSuccessResponse(analysisService.countTotalNumByUnit(eventId, unitType, startDate, endDate));
				} else if (DataEvent.EVENT_TYPE_RATE == eventType) {
					return CommonResponse.getSuccessResponse(analysisService.getAnalysisResultByUnit(eventId, unitType, startDate, endDate));
				}
			}
		}

		return CommonResponse.getErrorResponse();
	}

	private int getUnitType(Event event) {
		int targetType = event.getTargetType();
		if (targetType == DataEvent.TARGET_TYPE_COMMUNITY)
			return 2;
		if (targetType == DataEvent.TARGET_TYPE_HOSPITAL)
			return 1;
		return 0;
	}

}

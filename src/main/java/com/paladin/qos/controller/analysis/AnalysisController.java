package com.paladin.qos.controller.analysis;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.analysis.DataConstantContainer;
import com.paladin.qos.analysis.DataConstantContainer.Event;
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
	private AnalysisService analysisService;

	@GetMapping("/processed/index")
	public Object dataIndex() {
		return "/qos/analysis/processed_index";
	}

	@GetMapping("/view/{name}")
	public Object viewInex(@PathVariable("name") String name) {
		return "/qos/analysis/view_" + name;
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
				Event event = DataConstantContainer.getEvent(eventId);
				if (event != null) {
					int unitType = getUnitType(event);
					DataResult<DataPointDay> item = analysisService.getDataSetOfDay(eventId, unitType, startDate, endDate);
					if (item != null) {
						map.put(eventId, item);
					}
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
				Event event = DataConstantContainer.getEvent(eventId);
				if (event != null) {
					int unitType = getUnitType(event);
					DataResult<DataPointMonth> item = analysisService.getDataSetOfMonth(eventId, unitType, startDate, endDate);
					if (item != null) {
						map.put(eventId, item);
					}
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
				Event event = DataConstantContainer.getEvent(eventId);
				if (event != null) {
					int unitType = getUnitType(event);
					DataResult<DataPointYear> item = analysisService.getDataSetOfYear(eventId, unitType, startYear, endYear);
					if (item != null) {
						map.put(eventId, item);
					}
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
	public Object getProcessedDataByUnit(AnalysisRequest request) {
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

	@RequestMapping(value = "/data/get/total", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object getProcessedDataTotal(AnalysisRequest request) {
		Date startDate = request.getStartTime();
		Date endDate = request.getEndTime();

		List<String> eventIds = request.getEventIds();

		if (eventIds != null && eventIds.size() > 0) {
			Map<String, Long> map = new HashMap<>();
			for (String eventId : eventIds) {
				Event event = DataConstantContainer.getEvent(eventId);
				if (event != null) {
					long count = analysisService.getTotalNumOfEvent(eventId,  startDate, endDate);
					map.put(eventId, count);
				}
			}
			return CommonResponse.getSuccessResponse(map);
		} else {
			String eventId = request.getEventId();
			return CommonResponse.getSuccessResponse(analysisService.getTotalNumOfEvent(eventId,  startDate, endDate));
		}
	}

	private int getUnitType(Event event) {
		int targetType = event.getTargetType();
		if (targetType == DataEvent.TARGET_TYPE_COMMUNITY)
			return 2;
		if (targetType == DataEvent.TARGET_TYPE_HOSPITAL)
			return 1;
		return 0;
	}

	@GetMapping("/constant/event")
	@ResponseBody
	public Object getEvent() {
		return CommonResponse.getSuccessResponse(DataConstantContainer.getEventList());
	}

}

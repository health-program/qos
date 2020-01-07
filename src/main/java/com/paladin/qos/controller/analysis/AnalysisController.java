package com.paladin.qos.controller.analysis;

import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.analysis.DataConstantContainer;
import com.paladin.qos.analysis.DataConstantContainer.Event;
import com.paladin.qos.analysis.TimeUtil;
import com.paladin.qos.core.QosUserSession;
import com.paladin.qos.model.data.DataEvent;
import com.paladin.qos.service.analysis.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public Object viewInex(@PathVariable("name") String name, Model model) {
		QosUserSession userSession = QosUserSession.getCurrentUserSession();
		String[] agencyIds = userSession.getAgencyIds();
		if ( agencyIds != null && agencyIds.length == 1){
			model.addAttribute("agencyId",agencyIds[0]);
		}
		return "/qos/analysis/view_" + name;
	}

	@PostMapping("/data/get/day/instalments")
	@ResponseBody
	public Object getDataOfDayByInstalments(AnalysisRequest request) {
		List<String> eventIds = request.getEventIds();
		Date startDate = request.getStartTime();
		Date endDate = request.getEndTime();
		boolean byUnit = request.getByUnit() == 1;
		List<String> ignoreUnitIds = request.getIgnoreUnitIds();

		if (eventIds != null && eventIds.size() > 0) {
			Map<String, Object> map = new HashMap<>();
			for (String eventId : eventIds) {
				Event event = DataConstantContainer.getEvent(eventId);
				if (event != null) {
					int unitType = getUnitType(event);
					Object item = byUnit ? analysisService.getDataSetOfDay(eventId, unitType, startDate, endDate, ignoreUnitIds)
							: analysisService.getAnalysisResultByDay(eventId, unitType, startDate, endDate, ignoreUnitIds);
					if (item != null) {
						map.put(eventId, item);
					}
				}
			}
			return CommonResponse.getSuccessResponse(map);
		} else {
			String eventId = request.getEventId();
			return CommonResponse.getSuccessResponse(analysisService.getDataSetOfDay(eventId, startDate, endDate, ignoreUnitIds));
		}
	}

	@PostMapping("/data/get/month/instalments")
	@ResponseBody
	public Object getDataOfMonthByInstalments(AnalysisRequest request) {
		List<String> eventIds = request.getEventIds();
		Date startDate = request.getStartTime();
		Date endDate = request.getEndTime();
		boolean byUnit = request.getByUnit() == 1;
		List<String> ignoreUnitIds = request.getIgnoreUnitIds();

		if (eventIds != null && eventIds.size() > 0) {
			Map<String, Object> map = new HashMap<>();
			for (String eventId : eventIds) {
				Event event = DataConstantContainer.getEvent(eventId);
				if (event != null) {
					int unitType = getUnitType(event);
					Object item = byUnit ? analysisService.getDataSetOfMonth(eventId, unitType, startDate, endDate, ignoreUnitIds)
							: analysisService.getAnalysisResultByMonth(eventId, unitType, startDate, endDate, ignoreUnitIds);
					if (item != null) {
						map.put(eventId, item);
					}
				}
			}
			return CommonResponse.getSuccessResponse(map);
		} else {
			String eventId = request.getEventId();
			return CommonResponse.getSuccessResponse(analysisService.getDataSetOfMonth(eventId, startDate, endDate, ignoreUnitIds));
		}
	}

	@PostMapping("/data/get/year/instalments")
	@ResponseBody
	public Object getDataOfYearByInstalments(AnalysisRequest request) {
		Date startDate = request.getStartTime();
		Date endDate = request.getEndTime();
		int startYear = TimeUtil.getYear(startDate);
		int endYear = TimeUtil.getYear(endDate);
		boolean byUnit = request.getByUnit() == 1;
		List<String> ignoreUnitIds = request.getIgnoreUnitIds();

		List<String> eventIds = request.getEventIds();
		if (eventIds != null && eventIds.size() > 0) {
			Map<String, Object> map = new HashMap<>();
			for (String eventId : eventIds) {
				Event event = DataConstantContainer.getEvent(eventId);
				if (event != null) {
					int unitType = getUnitType(event);
					Object item = byUnit ? analysisService.getDataSetOfYear(eventId, unitType, startYear, endYear, ignoreUnitIds)
							: analysisService.getAnalysisResultByYear(eventId, unitType, startYear, endYear, ignoreUnitIds);
					if (item != null) {
						map.put(eventId, item);
					}
				}
			}
			return CommonResponse.getSuccessResponse(map);
		} else {
			String eventId = request.getEventId();
			return CommonResponse.getSuccessResponse(analysisService.getDataSetOfYear(eventId, startYear, endYear, ignoreUnitIds));
		}
	}

	@RequestMapping(value = "/data/get/unit", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object getProcessedDataByUnit(AnalysisRequest request) {
		Date startDate = request.getStartTime();
		Date endDate = request.getEndTime();
		List<String> ignoreUnitIds = request.getIgnoreUnitIds();

		List<String> eventIds = request.getEventIds();
		if (eventIds != null && eventIds.size() > 0) {
			Map<String, Object> map = new HashMap<>();
			for (String eventId : eventIds) {
				Event event = DataConstantContainer.getEvent(eventId);
				if (event != null) {
					int eventType = event.getEventType();
					int unitType = getUnitType(event);
					if (DataEvent.EVENT_TYPE_COUNT == eventType) {
						Object item = analysisService.countTotalNumByUnit(eventId, unitType, startDate, endDate, ignoreUnitIds);
						if (item != null) {
							map.put(eventId, item);
						}
					} else if (DataEvent.EVENT_TYPE_RATE == eventType) {
						Object item = analysisService.getAnalysisResultByUnit(eventId, unitType, startDate, endDate, ignoreUnitIds);
						if (item != null) {
							map.put(eventId, item);
						}
					}else if (DataEvent.EVENT_TYPE_MAX == eventType) {
						Object item = analysisService.countMaxNumByUnit(eventId, unitType, startDate, endDate, ignoreUnitIds);
						if (item != null) {
							map.put(eventId, item);
						}
					}else if (DataEvent.EVENT_TYPE_MIN == eventType) {
						Object item = analysisService.countMinNumByUnit(eventId, unitType, startDate, endDate, ignoreUnitIds);
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
					return CommonResponse.getSuccessResponse(analysisService.countTotalNumByUnit(eventId, unitType, startDate, endDate, ignoreUnitIds));
				} else if (DataEvent.EVENT_TYPE_RATE == eventType) {
					return CommonResponse.getSuccessResponse(analysisService.getAnalysisResultByUnit(eventId, unitType, startDate, endDate, ignoreUnitIds));
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
		List<String> ignoreUnitIds = request.getIgnoreUnitIds();

		List<String> eventIds = request.getEventIds();

		if (eventIds != null && eventIds.size() > 0) {
			Map<String, Long> map = new HashMap<>();
			for (String eventId : eventIds) {
				Event event = DataConstantContainer.getEvent(eventId);
				if (event != null) {
					long count = analysisService.getTotalNumOfEvent(eventId, startDate, endDate, ignoreUnitIds);
					map.put(eventId, count);
				}
			}
			return CommonResponse.getSuccessResponse(map);
		} else {
			String eventId = request.getEventId();
			return CommonResponse.getSuccessResponse(analysisService.getTotalNumOfEvent(eventId, startDate, endDate, ignoreUnitIds));
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

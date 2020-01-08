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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

	public static final Map<String,String> userNameMap;
	static{
		userNameMap = new HashMap<String, String>();
		userNameMap.put("KSDYRMYY", "320583467170249");
		userNameMap.put("KSDERMYY", "320583467170513");
		userNameMap.put("KSZYY", "320583467170257");
	   	userNameMap.put("KSDSRMYY", "320583467170265");
	   	userNameMap.put("KSJXRMYY", "320583467170337");
	   	userNameMap.put("KSQDRMYY", "320583467170345");
	   	userNameMap.put("KSBCRMYY", "320583467170353");
	   	userNameMap.put("KSDSIRMYY", "320583467170361");
	   	userNameMap.put("KSZSRMYY", "320583467170417");
	   	userNameMap.put("KSHQRMYY", "320583467170441");
	   	userNameMap.put("KSDSHRMYY", "320583467170468");
	   	userNameMap.put("KSDLRMYY", "320583467170476");
	   	userNameMap.put("KSZZRMYY", "320583467170505");
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
	public Object getProcessedDataByUnit(AnalysisRequest request,String userName) {
		Date startDate = request.getStartTime();
		Date endDate = request.getEndTime();
		List<String> ignoreUnitIds = request.getIgnoreUnitIds();

		List<String> eventIds = request.getEventIds();
		
		List<String> userNameList = new ArrayList<String>();
		userNameList.add("KSDYRMYY");userNameList.add("KSDERMYY");userNameList.add("KSZYY");userNameList.add("KSDSRMYY");
		userNameList.add("KSJXRMYY");userNameList.add("KSQDRMYY");userNameList.add("KSBCRMYY");userNameList.add("KSDSIRMYY");
		userNameList.add("KSZSRMYY");userNameList.add("KSHQRMYY");userNameList.add("KSDSHRMYY");userNameList.add("KSDLRMYY");
		userNameList.add("KSZZRMYY");
		boolean b = userNameList.contains(userName);
		if (eventIds != null && eventIds.size() > 0) {
			Map<String, Object> map = new HashMap<>();
			for (String eventId : eventIds) {
				Event event = DataConstantContainer.getEvent(eventId);
				if (event != null) {
					int eventType = event.getEventType();
					int unitType = getUnitType(event);
					if (DataEvent.EVENT_TYPE_COUNT == eventType) {
						Object item;
						if(StringUtils.isEmpty(userName)||!userNameList.contains(userName)){
							 item = analysisService.countTotalNumByUnit(eventId, unitType, startDate, endDate, ignoreUnitIds);
						}else{
							 item = analysisService.countTotalNumByUnitAndUserName(eventId, unitType, startDate, endDate, ignoreUnitIds,userNameMap.get(userName));
						}
						
						if (item != null) {
							map.put(eventId, item);
						}
					} else if (DataEvent.EVENT_TYPE_RATE == eventType) {
						
						Object item;
						if(StringUtils.isEmpty(userName)||!userNameList.contains(userName)){
							 item = analysisService.getAnalysisResultByUnit(eventId, unitType, startDate, endDate, ignoreUnitIds);
						}else{
							 item = analysisService.getAnalysisResultByUnitAndUserName(eventId, unitType, startDate, endDate, ignoreUnitIds,userNameMap.get(userName));
						}
						
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

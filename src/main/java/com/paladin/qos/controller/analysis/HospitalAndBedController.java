package com.paladin.qos.controller.analysis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.analysis.DataConstantContainer;
import com.paladin.qos.analysis.DataConstantContainer.Event;
import com.paladin.qos.model.data.DataEvent;
import com.paladin.qos.service.analysis.AnalysisService;

/**
 * 住院人次数，出院人次数，在院人数，额定床位，使用床位，病床使用率
 * 
 * @author FM
 *
 */
@Controller
@RequestMapping("/qos/analysis")
public class HospitalAndBedController {

	@Autowired
	private AnalysisService analysisService;

	private int getUnitType(Event event) {
		int targetType = event.getTargetType();
		if (targetType == DataEvent.TARGET_TYPE_COMMUNITY)
			return 2;
		if (targetType == DataEvent.TARGET_TYPE_HOSPITAL)
			return 1;
		return 0;
	}

	@RequestMapping(value = "/data/get/bed", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object getBed(AnalysisRequest request) {

		String eventId = request.getEventId();
		List<String> ignoreUnitIds = request.getIgnoreUnitIds();
		if (eventId != null && eventId != "") {
			Map<String, Object> map = new HashMap<>();

			Event event = DataConstantContainer.getEvent(eventId);
			if (event != null) {
				int eventType = event.getEventType();
				int unitType = getUnitType(event);
				if (DataEvent.EVENT_TYPE_COUNT == eventType) {
					Object item = analysisService.getLastCountByUnit(eventId, unitType,ignoreUnitIds);
					if (item != null) {
						map.put(eventId, item);
					}
				}
			}
			return CommonResponse.getSuccessResponse(map);
		}
		return CommonResponse.getErrorResponse();
	}

}

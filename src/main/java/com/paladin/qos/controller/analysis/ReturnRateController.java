package com.paladin.qos.controller.analysis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.service.analysis.AnalysisService;
import com.paladin.qos.service.analysis.data.AnalysisMonth;
import com.paladin.qos.service.analysis.data.AnalysisUnit;
import com.paladin.qos.service.analysis.data.ReturnRate;

@Controller
@RequestMapping("/qos/analysis")
public class ReturnRateController {

	@Autowired
	private AnalysisService analysisService;

	@GetMapping("/data/toReturnRate")
	public Object deathRate() {
		return "/qos/analysis/return_rate";
	}

	@RequestMapping(value = "/data/returnRate", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object getReturnRate(AnalysisRequest request) {
		String eventId = request.getEventId();
		Date startDate = request.getStartTime();
		Date endDate = request.getEndTime();
		List<String> eventIds = new ArrayList<String>();
		eventIds.add("11100");
		eventIds.add("11101");
		eventIds.add("11102");
		eventIds.add("11103");
		eventIds.add("11104");
		eventIds.add("11105");
		eventIds.add("11106");
		eventIds.add("11107");
		eventIds.add("11108");
		eventIds.add("11109");
		eventIds.add("11110");
		eventIds.add("11111");
		eventIds.add("11112");
		eventIds.add("11113");
		eventIds.add("11114");
		eventIds.add("11115");
		eventIds.add("11116");
		eventIds.add("11117");
		eventIds.add("11118");

		if (startDate == null || eventId == null || eventId.length() == 0) {
			return CommonResponse.getFailResponse();
		}

		if (endDate == null) {
			endDate = new Date();
		}

		List<ReturnRate> returnRateList = new ArrayList<ReturnRate>();
		for (int i = 0; i < eventIds.size(); i++) {
			List<AnalysisUnit> analysisResultByUnit = analysisService.getAnalysisResultByUnit(eventIds.get(i), startDate, endDate);
			for (AnalysisUnit analysisUnit : analysisResultByUnit) {
				ReturnRate aaa = new ReturnRate();
				aaa.setUnitId(analysisUnit.getUnitId());
				if (eventIds.get(i) == "11100") {
					aaa.setWithin31DaysEventNum(analysisUnit.getEventNum());
					aaa.setWithin31DaysTotalNum(analysisUnit.getTotalNum());
				}
				if (eventIds.get(i) == "11101") {
					aaa.setInfarctionEventNum(analysisUnit.getEventNum());
					aaa.setInfarctionTotalNum(analysisUnit.getTotalNum());
				}
				if (eventIds.get(i) == "11102") {
					aaa.setHeartFailureEventNum(analysisUnit.getEventNum());
					aaa.setHeartFailureTotalNum(analysisUnit.getTotalNum());
				}
				if (eventIds.get(i) == "11103") {
					aaa.setBrainBleedingEventNum(analysisUnit.getEventNum());
					aaa.setBrainBleedingTotalNum(analysisUnit.getTotalNum());
				}
				if (eventIds.get(i) == "11104") {
					aaa.setBrainInjuryEventNum(analysisUnit.getEventNum());
					aaa.setBrainInjuryTotalNum(analysisUnit.getTotalNum());
				}
				if (eventIds.get(i) == "11105") {
					aaa.setGastrointestinalBleedingEventNum(analysisUnit.getEventNum());
					aaa.setGastrointestinalBleedingTotalNum(analysisUnit.getTotalNum());
				}
				if (eventIds.get(i) == "11106") {
					aaa.setBodyDamageEventNum(analysisUnit.getEventNum());
					aaa.setBodyDamageTotaltNum(analysisUnit.getTotalNum());
				}
				if (eventIds.get(i) == "11107") {
					aaa.setPneumoniaEventNum(analysisUnit.getEventNum());
					aaa.setPneumoniaTotalNum(analysisUnit.getTotalNum());
				}
				if (eventIds.get(i) == "11108") {
					aaa.setLungDiseaseEventNum(analysisUnit.getEventNum());
					aaa.setLungDiseaseTotalNum(analysisUnit.getTotalNum());
				}
				if (eventIds.get(i) == "11109") {
					aaa.setDiabetesEventNum(analysisUnit.getEventNum());
					aaa.setDiabetesTotalNum(analysisUnit.getTotalNum());
				}
				if (eventIds.get(i) == "11110") {
					aaa.setGoiterEventNum(analysisUnit.getEventNum());
					aaa.setGoiterTotalNum(analysisUnit.getTotalNum());
				}
				if (eventIds.get(i) == "11111") {
					aaa.setAppendicitisEventNum(analysisUnit.getEventNum());
					aaa.setAppendicitisTotalNum(analysisUnit.getTotalNum());
				}
				if (eventIds.get(i) == "11112") {
					aaa.setProstateEventNum(analysisUnit.getEventNum());
					aaa.setProstateTotalNum(analysisUnit.getTotalNum());
				}
				if (eventIds.get(i) == "11113") {
					aaa.setKidneyFailureEventNum(analysisUnit.getEventNum());
					aaa.setKidneyFailureTotalNum(analysisUnit.getTotalNum());
				}
				if (eventIds.get(i) == "11114") {
					aaa.setBloodPoisoningEventNum(analysisUnit.getEventNum());
					aaa.setBloodPoisoningTotalNum(analysisUnit.getTotalNum());
				}
				if (eventIds.get(i) == "11115") {
					aaa.setHypertensionEventNum(analysisUnit.getEventNum());
					aaa.setHypertensionTotalNum(analysisUnit.getTotalNum());
				}
				if (eventIds.get(i) == "11116") {
					aaa.setPancreatitisEventNum(analysisUnit.getEventNum());
					aaa.setPancreatitisTotalNum(analysisUnit.getTotalNum());
				}
				if (eventIds.get(i) == "11117") {
					aaa.setCancerEventNum(analysisUnit.getEventNum());
					aaa.setCancerTotalNum(analysisUnit.getTotalNum());
				}
				if (eventIds.get(i) == "11118") {
					aaa.setMalignantTumorEventNum(analysisUnit.getEventNum());
					aaa.setMalignantTumorTotalNum(analysisUnit.getTotalNum());
				}

				returnRateList.add(aaa);
			}

		}

		return CommonResponse.getSuccessResponse(returnRateList);
	}

	@RequestMapping(value = "/data/getReturnDateByMonth", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object getDeathByMonth(AnalysisRequest request) {
		// String eventId = request.getEventId();
		Date startDate = request.getStartTime();
		Date endDate = request.getEndTime();
		List<String> eventIds = new ArrayList<String>();
		eventIds.add("11100");
		eventIds.add("11101");
		eventIds.add("11102");
		eventIds.add("11103");
		eventIds.add("11104");
		eventIds.add("11105");
		eventIds.add("11106");
		eventIds.add("11107");
		eventIds.add("11108");
		eventIds.add("11109");
		eventIds.add("11110");
		eventIds.add("11111");
		eventIds.add("11112");
		eventIds.add("11113");
		eventIds.add("11114");
		eventIds.add("11115");
		eventIds.add("11116");
		eventIds.add("11117");
		eventIds.add("11118");

		if (endDate == null) {
			endDate = new Date();
		}

		List<ReturnRate> returnRateList = new ArrayList<ReturnRate>();
		for (int i = 0; i < eventIds.size(); i++) {
			List<AnalysisMonth> analysisMonthList = analysisService.getAnalysisResultByMonth(eventIds.get(i), startDate, endDate);
			for (AnalysisMonth analysisMonth : analysisMonthList) {
				ReturnRate aaa = new ReturnRate();
				// aaa.setUnitId(analysisUnit.getUnitId());
				aaa.setYear(analysisMonth.getYear());
				aaa.setMonth(analysisMonth.getMonth());
				if (eventIds.get(i) == "11100") {
					aaa.setWithin31DaysEventNum(analysisMonth.getEventNum());
					aaa.setWithin31DaysTotalNum(analysisMonth.getTotalNum());
				}
				if (eventIds.get(i) == "11101") {
					aaa.setInfarctionEventNum(analysisMonth.getEventNum());
					aaa.setInfarctionTotalNum(analysisMonth.getTotalNum());
				}
				if (eventIds.get(i) == "11102") {
					aaa.setHeartFailureEventNum(analysisMonth.getEventNum());
					aaa.setHeartFailureTotalNum(analysisMonth.getTotalNum());
				}
				if (eventIds.get(i) == "11103") {
					aaa.setBrainBleedingEventNum(analysisMonth.getEventNum());
					aaa.setBrainBleedingTotalNum(analysisMonth.getTotalNum());
				}
				if (eventIds.get(i) == "11104") {
					aaa.setBrainInjuryEventNum(analysisMonth.getEventNum());
					aaa.setBrainInjuryTotalNum(analysisMonth.getTotalNum());
				}
				if (eventIds.get(i) == "11105") {
					aaa.setGastrointestinalBleedingEventNum(analysisMonth.getEventNum());
					aaa.setGastrointestinalBleedingTotalNum(analysisMonth.getTotalNum());
				}
				if (eventIds.get(i) == "11106") {
					aaa.setBodyDamageEventNum(analysisMonth.getEventNum());
					aaa.setBodyDamageTotaltNum(analysisMonth.getTotalNum());
				}
				if (eventIds.get(i) == "11107") {
					aaa.setPneumoniaEventNum(analysisMonth.getEventNum());
					aaa.setPneumoniaTotalNum(analysisMonth.getTotalNum());
				}
				if (eventIds.get(i) == "11108") {
					aaa.setLungDiseaseEventNum(analysisMonth.getEventNum());
					aaa.setLungDiseaseTotalNum(analysisMonth.getTotalNum());
				}
				if (eventIds.get(i) == "11109") {
					aaa.setDiabetesEventNum(analysisMonth.getEventNum());
					aaa.setDiabetesTotalNum(analysisMonth.getTotalNum());
				}
				if (eventIds.get(i) == "11110") {
					aaa.setGoiterEventNum(analysisMonth.getEventNum());
					aaa.setGoiterTotalNum(analysisMonth.getTotalNum());
				}
				if (eventIds.get(i) == "11111") {
					aaa.setAppendicitisEventNum(analysisMonth.getEventNum());
					aaa.setAppendicitisTotalNum(analysisMonth.getTotalNum());
				}
				if (eventIds.get(i) == "11112") {
					aaa.setProstateEventNum(analysisMonth.getEventNum());
					aaa.setProstateTotalNum(analysisMonth.getTotalNum());
				}
				if (eventIds.get(i) == "11113") {
					aaa.setKidneyFailureEventNum(analysisMonth.getEventNum());
					aaa.setKidneyFailureTotalNum(analysisMonth.getTotalNum());
				}
				if (eventIds.get(i) == "11114") {
					aaa.setBloodPoisoningEventNum(analysisMonth.getEventNum());
					aaa.setBloodPoisoningTotalNum(analysisMonth.getTotalNum());
				}
				if (eventIds.get(i) == "11115") {
					aaa.setHypertensionEventNum(analysisMonth.getEventNum());
					aaa.setHypertensionTotalNum(analysisMonth.getTotalNum());
				}
				if (eventIds.get(i) == "11116") {
					aaa.setPancreatitisEventNum(analysisMonth.getEventNum());
					aaa.setPancreatitisTotalNum(analysisMonth.getTotalNum());
				}
				if (eventIds.get(i) == "11117") {
					aaa.setCancerEventNum(analysisMonth.getEventNum());
					aaa.setCancerTotalNum(analysisMonth.getTotalNum());
				}
				if (eventIds.get(i) == "11118") {
					aaa.setMalignantTumorEventNum(analysisMonth.getEventNum());
					aaa.setMalignantTumorTotalNum(analysisMonth.getTotalNum());
				}

				returnRateList.add(aaa);
			}

		}
		return CommonResponse.getSuccessResponse(returnRateList);
	}
}

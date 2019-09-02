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
import com.paladin.qos.service.analysis.data.MajorSurgery;

@Controller
@RequestMapping("/qos/analysis")
public class MajorSurgeryController {

	@Autowired
	private AnalysisService analysisService;

	@GetMapping("/data/toMajorSurgery")
	public Object deathRate() {
		return "/qos/analysis/major_surgery";
	}

	@RequestMapping(value = "/data/majorSurgery", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object getDeathRate(AnalysisRequest request) {
		String eventId = request.getEventId();
		Date startDate = request.getStartTime();
		Date endDate = request.getEndTime();
		List<String> eventIds = new ArrayList<String>();
		eventIds.add("12100");
		eventIds.add("12101");
		eventIds.add("12102");
		eventIds.add("12103");
		eventIds.add("12104");

		if (startDate == null || eventId == null || eventId.length() == 0) {
			return CommonResponse.getFailResponse();
		}

		if (endDate == null) {
			endDate = new Date();
		}

		List<MajorSurgery> majorSurgeryList = new ArrayList<MajorSurgery>();
		for (int i = 0; i < eventIds.size(); i++) {
			List<AnalysisUnit> analysisResultByUnit = analysisService.getAnalysisResultByUnit(eventIds.get(i), startDate, endDate);
			for (AnalysisUnit analysisUnit : analysisResultByUnit) {
				MajorSurgery aaa = new MajorSurgery();
				aaa.setUnitId(analysisUnit.getUnitId());
				if (eventIds.get(i) == "12100") {
					aaa.setBypassGraftingEventNum(analysisUnit.getEventNum());
					aaa.setBypassGraftingTotalNum(analysisUnit.getTotalNum());
				}
				if (eventIds.get(i) == "12101") {
					aaa.setInterventionEventNum(analysisUnit.getEventNum());
					aaa.setInterventionTotalNum(analysisUnit.getTotalNum());
				}
				if (eventIds.get(i) == "12102") {
					aaa.setReplacementEventNum(analysisUnit.getEventNum());
					aaa.setReplacementTotalNum(analysisUnit.getTotalNum());
				}
				if (eventIds.get(i) == "12103") {
					aaa.setBrainSurgeryEventNum(analysisUnit.getEventNum());
					aaa.setBrainSurgeryTotalNum(analysisUnit.getTotalNum());
				}
				if (eventIds.get(i) == "12104") {
					aaa.setCesareanEventNum(analysisUnit.getEventNum());
					aaa.setCesareanTotalNum(analysisUnit.getTotalNum());
				}

				majorSurgeryList.add(aaa);
			}

		}

		return CommonResponse.getSuccessResponse(majorSurgeryList);
	}

	@RequestMapping(value = "/data/getMajorSurgeryByMonth", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object getDeathByMonth(AnalysisRequest request) {
		//String eventId = request.getEventId();
		Date startDate = request.getStartTime();
		Date endDate = request.getEndTime();
		List<String> eventIds = new ArrayList<String>();
		eventIds.add("12100");
		eventIds.add("12101");
		eventIds.add("12102");
		eventIds.add("12103");
		eventIds.add("12104");

		if (endDate == null) {
			endDate = new Date();
		}

		List<MajorSurgery> majorSurgeryList = new ArrayList<MajorSurgery>();
		for (int i = 0; i < eventIds.size(); i++) {
			List<AnalysisMonth> analysisMonthList = analysisService.getAnalysisResultByMonth(eventIds.get(i), startDate, endDate);
			for (AnalysisMonth analysisMonth : analysisMonthList) {
				MajorSurgery aaa = new MajorSurgery();
				// aaa.setUnitId(analysisUnit.getUnitId());
				aaa.setYear(analysisMonth.getYear());
				aaa.setMonth(analysisMonth.getMonth());
				if (eventIds.get(i) == "12100") {
					aaa.setBypassGraftingEventNum(analysisMonth.getEventNum());
					aaa.setBypassGraftingTotalNum(analysisMonth.getTotalNum());
				}
				if (eventIds.get(i) == "12101") {
					aaa.setInterventionEventNum(analysisMonth.getEventNum());
					aaa.setInterventionTotalNum(analysisMonth.getTotalNum());
				}
				if (eventIds.get(i) == "12102") {
					aaa.setReplacementEventNum(analysisMonth.getEventNum());
					aaa.setReplacementTotalNum(analysisMonth.getTotalNum());
				}
				if (eventIds.get(i) == "12103") {
					aaa.setBrainSurgeryEventNum(analysisMonth.getEventNum());
					aaa.setBrainSurgeryTotalNum(analysisMonth.getTotalNum());
				}
				if (eventIds.get(i) == "12104") {
					aaa.setCesareanEventNum(analysisMonth.getEventNum());
					aaa.setCesareanTotalNum(analysisMonth.getTotalNum());
				}

				majorSurgeryList.add(aaa);
			}

		}
		System.out.println("zsfdsds" + majorSurgeryList);
		return CommonResponse.getSuccessResponse(majorSurgeryList);
	}
}

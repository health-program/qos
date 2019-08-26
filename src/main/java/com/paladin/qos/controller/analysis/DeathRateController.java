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
import com.paladin.qos.service.analysis.data.DeathRate;

@Controller
@RequestMapping("/qos/analysis")
public class DeathRateController {

	@Autowired
	private AnalysisService analysisService;

	@GetMapping("/data/toDeathRate")
	public Object deathRate() {
		return "/qos/analysis/death_rate";
	}

	@RequestMapping(value = "/data/deathRate", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object getDeathRate(AnalysisRequest request) {
		String eventId = request.getEventId();
		Date startDate = request.getStartTime();
		Date endDate = request.getEndTime();
		List<String> eventIds = new ArrayList<String>();
		eventIds.add("10100");
		eventIds.add("10110");
		eventIds.add("10111");
		eventIds.add("10112");
		eventIds.add("10113");
		eventIds.add("10114");
		eventIds.add("10115");
		eventIds.add("10116");

		if (startDate == null || eventId == null || eventId.length() == 0) {
			return CommonResponse.getFailResponse();
		}

		if (endDate == null) {
			endDate = new Date();
		}
		// 10100";"10110";"10111";"10112";"10113";"10114";"10115";"10116";private String
		// unitId;eventNum;totalNum

		List<DeathRate> deathRateList = new ArrayList<DeathRate>();
		for (int i = 0; i < eventIds.size(); i++) {
			List<AnalysisUnit> analysisResultByUnit = analysisService.getAnalysisResultByUnit(eventIds.get(i), startDate, endDate);
			for (AnalysisUnit analysisUnit : analysisResultByUnit) {
				DeathRate aaa = new DeathRate();
				aaa.setUnitId(analysisUnit.getUnitId());
				if (eventIds.get(i) == "10100") {
					aaa.setDeathEventNum(analysisUnit.getEventNum());
					aaa.setDeathTotalNum(analysisUnit.getTotalNum());
				}
				if (eventIds.get(i) == "10110") {
					aaa.setNewBabyDeathEventNum(analysisUnit.getEventNum());
					aaa.setNewBabyDeathTotalNum(analysisUnit.getTotalNum());
				}
				if (eventIds.get(i) == "10111") {
					aaa.setNewBabyOperationDeathEventNum(analysisUnit.getEventNum());
					aaa.setNewBabyOperationDeathTotalNum(analysisUnit.getTotalNum());
				}
				if (eventIds.get(i) == "10112") {
					aaa.setNewBabyNonOperationDeathEventNum(analysisUnit.getEventNum());
					aaa.setNewBabyNonOperationDeathTotalNum(analysisUnit.getTotalNum());
				}
				if (eventIds.get(i) == "10113") {
					aaa.setNewBabyWeightOneEventNum(analysisUnit.getEventNum());
					aaa.setNewBabyWeightOneTotalNum(analysisUnit.getTotalNum());
				}
				if (eventIds.get(i) == "10114") {
					aaa.setNewBabyWeightTwoEventNum(analysisUnit.getEventNum());
					aaa.setNewBabyWeightTwoTotalNum(analysisUnit.getTotalNum());
				}
				if (eventIds.get(i) == "10115") {
					aaa.setNewBabyWeightThreeEventNum(analysisUnit.getEventNum());
					aaa.setNewBabyWeightThreeTotalNum(analysisUnit.getTotalNum());
				}
				if (eventIds.get(i) == "10116") {
					aaa.setNewBabyWeightFourEventNum(analysisUnit.getEventNum());
					aaa.setNewBabyWeightFourTotalNum(analysisUnit.getTotalNum());
				}

				deathRateList.add(aaa);
			}

		}

		return CommonResponse.getSuccessResponse(deathRateList);
	}

	@RequestMapping(value = "/data/getDeathByMonth", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object getDeathByMonth(AnalysisRequest request) {
		// String eventId = request.getEventId();
		Date startDate = request.getStartTime();
		Date endDate = request.getEndTime();
		List<String> eventIds = new ArrayList<String>();
		eventIds.add("10100");
		eventIds.add("10110");
		eventIds.add("10111");
		eventIds.add("10112");
		eventIds.add("10113");
		eventIds.add("10114");
		eventIds.add("10115");
		eventIds.add("10116");

		if (endDate == null) {
			endDate = new Date();
		}
		// 10100";"10110";"10111";"10112";"10113";"10114";"10115";"10116";private String
		// unitId;eventNum;totalNum

		List<DeathRate> deathRateList = new ArrayList<DeathRate>();
		for (int i = 0; i < eventIds.size(); i++) {
			List<AnalysisMonth> analysisMonthList = analysisService.getAnalysisResultByMonth(eventIds.get(i), startDate, endDate);
			for (AnalysisMonth analysisMonth : analysisMonthList) {
				DeathRate aaa = new DeathRate();
				// aaa.setUnitId(analysisUnit.getUnitId());
				aaa.setYear(analysisMonth.getYear());
				aaa.setMonth(analysisMonth.getMonth());
				if (eventIds.get(i) == "10100") {
					aaa.setDeathEventNum(analysisMonth.getEventNum());
					aaa.setDeathTotalNum(analysisMonth.getTotalNum());
				}
				if (eventIds.get(i) == "10110") {
					aaa.setNewBabyDeathEventNum(analysisMonth.getEventNum());
					aaa.setNewBabyDeathTotalNum(analysisMonth.getTotalNum());
				}
				if (eventIds.get(i) == "10111") {
					aaa.setNewBabyOperationDeathEventNum(analysisMonth.getEventNum());
					aaa.setNewBabyOperationDeathTotalNum(analysisMonth.getTotalNum());
				}
				if (eventIds.get(i) == "10112") {
					aaa.setNewBabyNonOperationDeathEventNum(analysisMonth.getEventNum());
					aaa.setNewBabyNonOperationDeathTotalNum(analysisMonth.getTotalNum());
					deathRateList.add(aaa);
				}
				if (eventIds.get(i) == "10113") {
					aaa.setNewBabyWeightOneEventNum(analysisMonth.getEventNum());
					aaa.setNewBabyWeightOneTotalNum(analysisMonth.getTotalNum());
				}
				if (eventIds.get(i) == "10114") {
					aaa.setNewBabyWeightTwoEventNum(analysisMonth.getEventNum());
					aaa.setNewBabyWeightTwoTotalNum(analysisMonth.getTotalNum());
				}
				if (eventIds.get(i) == "10115") {
					aaa.setNewBabyWeightThreeEventNum(analysisMonth.getEventNum());
					aaa.setNewBabyWeightThreeTotalNum(analysisMonth.getTotalNum());
				}
				if (eventIds.get(i) == "10116") {
					aaa.setNewBabyWeightFourEventNum(analysisMonth.getEventNum());
					aaa.setNewBabyWeightFourTotalNum(analysisMonth.getTotalNum());
				}

				deathRateList.add(aaa);
			}

		}
		return CommonResponse.getSuccessResponse(deathRateList);
	}
}

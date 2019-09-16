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
import com.paladin.qos.service.analysis.data.AnalysisUnit;
import com.paladin.qos.service.analysis.data.HospitalAndBed;
import com.paladin.qos.service.analysis.data.PatientsNum;
/**
 * 住院人次数，出院人次数，在院人数，额定床位，使用床位，病床使用率
 * @author FM
 *
 */
@Controller
@RequestMapping("/qos/analysis")
public class HospitalAndBedController {

	@Autowired
	private AnalysisService analysisService;
	
	@GetMapping("/data/toHospitalAndBed")
	public Object deathRate() {
		return "/qos/analysis/hospitalAndBed";
	}
	
	@RequestMapping(value = "/data/HospitalAndBen", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object PatientsNum(AnalysisRequest request){
		String eventId = request.getEventId();
		Date startDate = request.getStartTime();
		Date endDate = request.getEndTime();
		List<String> eventIds = new ArrayList<String>();
		eventIds.add("14001");
		eventIds.add("14002");
		eventIds.add("14003");
		eventIds.add("14004");
		eventIds.add("14005");
		eventIds.add("14006");
		
		if (startDate == null || eventId == null || eventId.length() == 0) {
			return CommonResponse.getFailResponse();
		}

		if (endDate == null) {
			endDate = new Date();
		}
		
		List<HospitalAndBed> hospitalAndBedList = new ArrayList<HospitalAndBed>();
		for (int i = 0; i < eventIds.size(); i++) {
			List<AnalysisUnit> analysisResultByUnit = analysisService.getAnalysisResultByUnit(eventIds.get(i), startDate, endDate);
			for (AnalysisUnit analysisUnit : analysisResultByUnit) {
				HospitalAndBed aaa = new HospitalAndBed();
				aaa.setUnitId(analysisUnit.getUnitId());
				if (eventIds.get(i) == "14001") {//住院人次数
					aaa.setInHospitalNum(analysisUnit.getEventNum());
				}
				if (eventIds.get(i) == "14002") {//出院人次数
					aaa.setOutHospitalNum(analysisUnit.getEventNum());
				}
				if (eventIds.get(i) == "14003") {//在院人次数
					aaa.setOnHospitalNum(analysisUnit.getEventNum());
				}
				if (eventIds.get(i) == "14004") {//额定床位
					aaa.setRatedBedNum(analysisUnit.getEventNum());
				}
				if (eventIds.get(i) == "14005") {//使用床位
					aaa.setBedInUsedNum(analysisUnit.getEventNum());
				}
				/*if (eventIds.get(i) == "14006") {//病床使用率
					aaa.setBedInUsedRate(analysisUnit.getEventNum());
				}*/

				hospitalAndBedList.add(aaa);
			}

		}

		return CommonResponse.getSuccessResponse(hospitalAndBedList);
		
		
	}
}

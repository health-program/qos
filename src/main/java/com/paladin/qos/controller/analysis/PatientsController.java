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
import com.paladin.qos.service.analysis.data.DeathRate;
import com.paladin.qos.service.analysis.data.PatientsNum;

/**
 * 门诊人次数，急诊人次数，门急诊人次数，出诊医生数，医生平均门急诊量，康复服务数
 * @author FM
 *
 */
@Controller
@RequestMapping("/qos/analysis")
public class PatientsController {

	@Autowired
	private AnalysisService analysisService;
	
	@GetMapping("/data/toPatients")
	public Object deathRate() {
		return "/qos/analysis/patients_num";
	}
	
	@RequestMapping(value = "/data/PatientsNum", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object PatientsNum(AnalysisRequest request){
		String eventId = request.getEventId();
		Date startDate = request.getStartTime();
		Date endDate = request.getEndTime();
		List<String> eventIds = new ArrayList<String>();
		eventIds.add("13001");
		eventIds.add("13002");
		eventIds.add("13003");
		eventIds.add("13004");
		eventIds.add("13005");
		eventIds.add("13006");
		
		if (startDate == null || eventId == null || eventId.length() == 0) {
			return CommonResponse.getFailResponse();
		}

		if (endDate == null) {
			endDate = new Date();
		}
		
		List<PatientsNum> patientsNumList = new ArrayList<PatientsNum>();
		for (int i = 0; i < eventIds.size(); i++) {
			List<AnalysisUnit> analysisResultByUnit = analysisService.getAnalysisResultByUnit(eventIds.get(i), startDate, endDate);
			for (AnalysisUnit analysisUnit : analysisResultByUnit) {
				PatientsNum aaa = new PatientsNum();
				aaa.setUnitId(analysisUnit.getUnitId());
				if (eventIds.get(i) == "13001") {//门诊
					aaa.setOutpatientNumber(analysisUnit.getEventNum());
				}
				if (eventIds.get(i) == "13002") {//急诊
					aaa.setEmergencyNumber(analysisUnit.getEventNum());
				}
				if (eventIds.get(i) == "13003") {//门急诊
					aaa.setPatientsNumber(analysisUnit.getEventNum());
				}
				if (eventIds.get(i) == "13004") {//出诊医生
					aaa.setVisitDoctorNumber(analysisUnit.getEventNum());
				}
				if (eventIds.get(i) == "13005") {//医生门诊量
					aaa.setAverageNumber(analysisUnit.getEventNum());
				}
				if (eventIds.get(i) == "13006") {//康复服务数
					aaa.setRecoveryNumber(analysisUnit.getEventNum());
				}

				patientsNumList.add(aaa);
			}

		}

		return CommonResponse.getSuccessResponse(patientsNumList);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

























































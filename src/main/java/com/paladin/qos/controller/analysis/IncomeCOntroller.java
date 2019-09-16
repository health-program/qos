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
import com.paladin.qos.service.analysis.data.Income;
import com.paladin.qos.service.analysis.data.PatientsNum;
/**
 * 合计总收入，医疗收入，药品收入，药占比，其他收入
 * @author FM
 *
 */
@Controller
@RequestMapping("/qos/analysis")
public class IncomeCOntroller {
	@Autowired
	private AnalysisService analysisService;
	
	@GetMapping("/data/toIncome")
	public Object deathRate() {
		return "/qos/analysis/income";
	}
	
	@RequestMapping(value = "/data/Income", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object Income(AnalysisRequest request){
		String eventId = request.getEventId();
		Date startDate = request.getStartTime();
		Date endDate = request.getEndTime();
		List<String> eventIds = new ArrayList<String>();
		eventIds.add("15001");
		eventIds.add("15002");
		eventIds.add("15003");
		eventIds.add("15004");
		
		if (startDate == null || eventId == null || eventId.length() == 0) {
			return CommonResponse.getFailResponse();
		}

		if (endDate == null) {
			endDate = new Date();
		}
		
		List<Income> incomeList = new ArrayList<Income>();
		for (int i = 0; i < eventIds.size(); i++) {
			List<AnalysisUnit> analysisResultByUnit = analysisService.getAnalysisResultByUnit(eventIds.get(i), startDate, endDate);
			for (AnalysisUnit analysisUnit : analysisResultByUnit) {
				Income aaa = new Income();
				aaa.setUnitId(analysisUnit.getUnitId());
				if (eventIds.get(i) == "15001") {//总收入
					aaa.setTotalMoney(analysisUnit.getEventNum());
				}
				if (eventIds.get(i) == "15002") {//医疗收入
					aaa.setMedicalMoney(analysisUnit.getEventNum());
				}
				if (eventIds.get(i) == "15003") {//药品收入
					aaa.setDrugMoney(analysisUnit.getEventNum());
				}
				if (eventIds.get(i) == "15004") {//其他收入
					aaa.setOtherMoney(analysisUnit.getEventNum());
				}
				

				incomeList.add(aaa);
			}

		}

		return CommonResponse.getSuccessResponse(incomeList);
	}
}

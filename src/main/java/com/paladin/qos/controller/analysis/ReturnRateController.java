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
import com.paladin.qos.analysis.DataProcessContainer;
import com.paladin.qos.service.analysis.AnalysisService;
import com.paladin.qos.service.analysis.data.AnalysisUnit;
import com.paladin.qos.service.analysis.data.DeathRate;
import com.paladin.qos.service.analysis.data.ReturnRate;

@Controller
@RequestMapping("/qos/analysis")
public class ReturnRateController {

	@Autowired
	private DataProcessContainer dataProcessContainer;

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
        eventIds.add("11100");eventIds.add("11101");eventIds.add("11102");eventIds.add("11103");
        eventIds.add("11104");eventIds.add("11105");eventIds.add("11106");eventIds.add("11107");
        eventIds.add("11108");eventIds.add("11109");eventIds.add("11110");eventIds.add("11111");
        eventIds.add("11112");eventIds.add("11113");eventIds.add("11114");eventIds.add("11115");
        eventIds.add("11116");eventIds.add("11117");eventIds.add("11118");
        
        
		if (startDate == null || eventId == null || eventId.length() == 0) {
			return CommonResponse.getFailResponse();
		}

		if (endDate == null) {
			endDate = new Date();
		}
		
		List<ReturnRate> returnRateList = new ArrayList<ReturnRate>();
		for(int i=0;i<eventIds.size();i++){
			List<AnalysisUnit> analysisResultByUnit = analysisService.getAnalysisResultByUnit(eventIds.get(i), startDate, endDate);
			for (AnalysisUnit analysisUnit : analysisResultByUnit) {
				ReturnRate aaa=new ReturnRate();
				aaa.setUnitId(analysisUnit.getUnitId());
				if(eventIds.get(i)=="11100"){

				}
				if(eventIds.get(i)=="11101"){
					
					
				}
				if(eventIds.get(i)=="11102"){
					
				}
				if(eventIds.get(i)=="11103"){
					
				}
				if(eventIds.get(i)=="11104"){
					
				}
				if(eventIds.get(i)=="11105"){
					
				}
				if(eventIds.get(i)=="11106"){
					
				}if(eventIds.get(i)=="11107"){
					
				}if(eventIds.get(i)=="11108"){
					
				}if(eventIds.get(i)=="11109"){
					
				}if(eventIds.get(i)=="11110"){
					
				}if(eventIds.get(i)=="11111"){
					
				}if(eventIds.get(i)=="11112"){
					
				}if(eventIds.get(i)=="11113"){
					
				}if(eventIds.get(i)=="11114"){
					
				}if(eventIds.get(i)=="11115"){
					
				}if(eventIds.get(i)=="11116"){
					
				}if(eventIds.get(i)=="11117"){
					
				}if(eventIds.get(i)=="11118"){
					
				}
				
				returnRateList.add(aaa);
			}
			
		}
		
		return CommonResponse.getSuccessResponse(returnRateList);
	}
}

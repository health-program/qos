package com.paladin.qos.controller;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.analysis.DataConstantContainer;
import com.paladin.qos.analysis.DataConstantContainer.Event;
import com.paladin.qos.analysis.TimeUtil;
import com.paladin.qos.controller.analysis.AnalysisRequest;
import com.paladin.qos.model.data.DataEvent;
import com.paladin.qos.model.register.Register;
import com.paladin.qos.service.analysis.AnalysisService;
import com.paladin.qos.service.analysis.data.DataCountUnit;

/**   
 * @author MyKite
 * @version 2019年6月24日 下午3:04:58 
 */
@Controller
@RequestMapping("/home/page/qos")
public class HomePageController {
	
	@Autowired
	private AnalysisService registerService;

    @RequestMapping(value = "/index")
	public String homeIndex(HttpServletRequest request){
	    return "/qos/homepage/index";
	}
    
    
    @RequestMapping(value = "/familydoctor")
  	public String familyIndex(HttpServletRequest request){
  	    return "/qos/homepage/familydoctor/index";
  	}



	@RequestMapping(value = "/publichealth/{name}")
	public String publichealthIndex(@PathVariable("name") String name){
		return "/qos/homepage/publichealth/"+name;
	}
	
	
	


	@RequestMapping(value = "/quailtydisplay")
	public String quailtydisplayIndex(HttpServletRequest request){
		return "/qos/homepage/quailtydisplay/index";
	}
	
	/**
	 * 患者列表
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/quailtydisplay/getRegisterList", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object getRegisterList(String id) {
		List<Register> registerList = registerService.findRegisterList();
		Collections.reverse(registerList);
		return CommonResponse.getSuccessResponse(registerList);
	}
	
	/**
	 * 社区当天门诊人次，社区当天急诊人次，当月门诊人次，当月急诊人次
	 */
	@RequestMapping(value = "/quailtydisplay/getOutPatientNumber", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object getOutPatientNumber() {
		Map<String,Object> map  = new HashMap<String,Object>();
		List<DataCountUnit> patientNumberList = registerService.getOutPatientNumber();//当天门诊人次
		List<DataCountUnit> emergencyNumberList = registerService.getEmergencyNumber();//当天急诊人次
		List<DataCountUnit> todayNumberList = registerService.getTodayNumber();//当月门诊人次
		List<DataCountUnit> thisMonthNumberList = registerService.getThisMonthNumber();//当月急诊人次
		map.put("patientNumberList",patientNumberList );
		map.put("emergencyNumberList", emergencyNumberList);
		map.put("todayNumberList",todayNumberList );
		map.put("thisMonthNumberList",thisMonthNumberList );
		
		return CommonResponse.getSuccessResponse(map);
	}
	
	
	/**
	 * 医院当天门诊人次，医院当天急诊人次，当月门诊人次，当月急诊人次
	 */
	@RequestMapping(value = "/hospital/getOutPatientNumber", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object getHospitalOutPatientNumber() {
		Map<String,Object> map  = new HashMap<String,Object>();
		List<DataCountUnit> patientNumberList = registerService.getHospitalOutPatientNumber();//当天门诊人次
		List<DataCountUnit> emergencyNumberList = registerService.getHospitalEmergencyNumber();//当天急诊人次
		List<DataCountUnit> todayNumberList = registerService.getHospitalTodayNumber();//当月门诊人次
		List<DataCountUnit> thisMonthNumberList = registerService.getHospitalThisMonthNumber();//当月急诊人次
		map.put("patientHospitalNumberList",patientNumberList );
		map.put("emergencyHospitalNumberList", emergencyNumberList);
		map.put("todayNumberHospitalList",todayNumberList );
		map.put("thisMonthNumberHospitalList",thisMonthNumberList );
		
		return CommonResponse.getSuccessResponse(map);
	}
	
	@RequestMapping(value = "/population/signing", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object populationSigningNum(){
	    return CommonResponse.getSuccessResponse(registerService.populationSigningNum());
	}
	
	    //门急诊折线图社区（今年，去年）13003
	    @RequestMapping(value = "/data/get/month/twoYear", method = { RequestMethod.GET, RequestMethod.POST })
		@ResponseBody
		public Object getTwoYear(){
		    return CommonResponse.getSuccessResponse(registerService.getTwoYear());
		}
}


 
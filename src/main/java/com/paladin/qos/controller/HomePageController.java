package com.paladin.qos.controller;

import java.util.Collections;
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
	 * 门诊人次，急诊人次，当日门急诊量，当月门急诊量
	 */
	@RequestMapping(value = "/quailtydisplay/getOutPatientNumber", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object getOutPatientNumber() {
		Map<String,Object> map  = new HashMap<String,Object>();
		List<DataCountUnit> patientNumberList = registerService.getOutPatientNumber();//门诊人次
		List<DataCountUnit> emergencyNumberList = registerService.getEmergencyNumber();//急诊人次
		List<DataCountUnit> todayNumberList = registerService.getTodayNumber();//当日门急诊量
		List<DataCountUnit> thisMonthNumberList = registerService.getThisMonthNumber();//当月门急诊量
		map.put("patientNumberList",patientNumberList );
		map.put("emergencyNumberList", emergencyNumberList);
		map.put("todayNumberList",todayNumberList );
		map.put("thisMonthNumberList",thisMonthNumberList );
		
		return CommonResponse.getSuccessResponse(map);
	}
	
	
	/**
	 * 医院门诊人次，医院急诊人次，医院当日门急诊量，医院当月门急诊量
	 */
	@RequestMapping(value = "/hospital/getOutPatientNumber", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object getHospitalOutPatientNumber() {
		Map<String,Object> map  = new HashMap<String,Object>();
		List<DataCountUnit> patientNumberList = registerService.getHospitalOutPatientNumber();//门诊人次
		List<DataCountUnit> emergencyNumberList = registerService.getHospitalEmergencyNumber();//急诊人次
		List<DataCountUnit> todayNumberList = registerService.getHospitalTodayNumber();//当日门急诊量
		List<DataCountUnit> thisMonthNumberList = registerService.getHospitalThisMonthNumber();//当月门急诊量
		map.put("patientHospitalNumberList",patientNumberList );
		map.put("emergencyHospitalNumberList", emergencyNumberList);
		map.put("todayNumberHospitalList",todayNumberList );
		map.put("thisMonthNumberHospitalList",thisMonthNumberList );
		
		return CommonResponse.getSuccessResponse(map);
	}
}


 
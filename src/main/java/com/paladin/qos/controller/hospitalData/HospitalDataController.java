package com.paladin.qos.controller.hospitalData;

import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.model.data.DataUnit;
import com.paladin.qos.model.hospitalData.HospitalDataCheck;
import com.paladin.qos.model.hospitalData.HospitalDataLog;
import com.paladin.qos.service.data.DataUnitService;
import com.paladin.qos.service.hospitalData.HospitalDataLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/qos/hospitalData")
public class HospitalDataController {
	@Autowired
	private HospitalDataLogService hospitalDataLogService;
	
	@Autowired
    private DataUnitService dataUnitService;
	
	@GetMapping("/log/index")
	public Object logInex(Model model) {
		
		List<Integer> types=new ArrayList<>();
        types.add(DataUnit.TYPE_HOSPITAL);
        model.addAttribute("unit", dataUnitService.selectData(types));
		return "/qos/hospitalData/log";
	}
	
	@RequestMapping(value = "/findLog/page", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Object findLog(HospitalDataLog query) {
        return CommonResponse.getSuccessResponse(hospitalDataLogService.searchFindPage(query));
    }
	
	@GetMapping("/check/index")
	public Object checkInex(Model model) {
		List<Integer> types=new ArrayList<>();
        types.add(DataUnit.TYPE_HOSPITAL);
        model.addAttribute("unit", dataUnitService.selectData(types));
        model.addAttribute("type", hospitalDataLogService.selectCheckType());
		return "/qos/hospitalData/check";
	}
	
	@RequestMapping(value = "/findCheck/page", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Object findPage(HospitalDataCheck query) {
        return CommonResponse.getSuccessResponse(hospitalDataLogService.searchFindCheckPage(query));
    }
}

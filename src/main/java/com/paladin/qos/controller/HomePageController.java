package com.paladin.qos.controller;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.analysis.DataConstantContainer;
import com.paladin.qos.model.register.Register;
import com.paladin.qos.service.analysis.AnalysisService;

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
	
	@RequestMapping(value = "/quailtydisplay/getRegisterList", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object getRegisterList(String id) {
		List<Register> registerList = registerService.findRegisterList();
		Collections.reverse(registerList);
		return CommonResponse.getSuccessResponse(registerList);
	}
}


 
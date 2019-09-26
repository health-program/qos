package com.paladin.qos.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**   
 * @author MyKite
 * @version 2019年6月24日 下午3:04:58 
 */
@Controller
@RequestMapping("/home/page/qos")
public class HomePageController {

    @RequestMapping(value = "/index")
	public String homeIndex(HttpServletRequest request){
	    return "/qos/homepage/index";
	}
    
    
    @RequestMapping(value = "/familydoctor")
  	public String familyIndex(HttpServletRequest request){
  	    return "/qos/homepage/familydoctor/index";
  	}




	@RequestMapping(value = "/publichealth")
	public String publichealthIndex(HttpServletRequest request){
		return "/qos/homepage/publichealth/index";
	}


	@RequestMapping(value = "/quailtydisplay")
	public String quailtydisplayIndex(HttpServletRequest request){
		return "/qos/homepage/quailtydisplay/index";
	}
}


 
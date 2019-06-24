package com.paladin.qos.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paladin.framework.core.GlobalProperties;

/**   
 * @author MyKite
 * @version 2019年6月24日 下午3:04:58 
 */
@Controller
@RequestMapping("/home/page/"+GlobalProperties.project+"")
public class HomePageController {

    	@RequestMapping(value = "/index")
	public String homeIndex(HttpServletRequest request){
	    return "/" + GlobalProperties.project + "/homepage/index";
	}
}

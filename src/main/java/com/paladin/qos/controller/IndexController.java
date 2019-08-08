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
@RequestMapping("/" + GlobalProperties.project)
public class IndexController {
    
    	@RequestMapping(value = "/familydoctor/index")
  	public String familyIndex(HttpServletRequest request){
  	    return "/" + GlobalProperties.project + "/homepage/familydoctor/index";
  	}
}


 
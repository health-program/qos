package com.paladin.qos.controller.school;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.qos.service.school.dto.SchoolBasicsQueryDTO;

/**   
 * @author MyKite
 * @version 2019年6月12日 下午3:36:14 
 */
@Controller
@RequestMapping("/qos/school/basics")
public class SchoolBasicsController extends ControllerSupport{

    @RequestMapping("/index")
    @QueryInputMethod(queryClass = SchoolBasicsQueryDTO.class)
    public String index(){
	return "/qos/school/school_basics_index";
    }
    
    @RequestMapping("/add")
    public String add(){
	return "/qos/school/school_basics_add";
    }
}

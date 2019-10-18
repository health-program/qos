package com.paladin.qos.controller.school;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.service.school.OrgSchoolNameService;
import com.paladin.qos.service.school.OrgSchoolPeopleService;
import com.paladin.qos.service.school.OrgSchoolService;

/**
 * < 班级管理>
 * 
 * @author MyKite
 * @version [版本号, 2019年7月23日]
 */
@Controller
@RequestMapping("/qos/org/school/people")
public class OrgSchoolPeopleController {

	@Autowired
	private OrgSchoolPeopleService orgSchoolPeopleService;

	@Autowired
	private OrgSchoolNameService orgSchoolNameService;

	@Autowired
	private OrgSchoolService orgSchoolService;

	@RequestMapping(value = "/findList", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object findListBySchoolId() {
		Map<String, Object> result = new HashMap<>();
		result.put("schoolYear", orgSchoolService.findAll());
		result.put("schoolName", orgSchoolNameService.findAll());
		result.put("schoolPeople", orgSchoolPeopleService.findAll());

		return CommonResponse.getSuccessResponse(result);
	}

}

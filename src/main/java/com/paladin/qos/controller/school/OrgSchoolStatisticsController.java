package com.paladin.qos.controller.school;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.service.school.OrgSchoolService;
import com.paladin.qos.service.school.dto.OrgSchoolQuery;

/**
 * @author MyKite
 * @version 2019年7月23日 上午11:11:28
 */

@Controller
@RequestMapping("/qos/org/school/statistic")
public class OrgSchoolStatisticsController extends ControllerSupport {

	@Autowired
	OrgSchoolService orgSchoolService;

	@RequestMapping(value = "/index")
	@QueryInputMethod(queryClass = OrgSchoolQuery.class)
	public String index() {
		return "/qos/school/org_school_personnel_statistics";
	}

	@RequestMapping(value = "/doctor/index")
	@QueryInputMethod(queryClass = OrgSchoolQuery.class)
	public String doctorIndex() {
		return "/qos/school/org_school_doctor_statistics";
	}

	/**
	 * 学校学额统计 统计每个学校学生总人数
	 * 
	 * @param query
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("/personnel/count")
	@ResponseBody
	public Object SchoolPersonnelStatistics(OrgSchoolQuery query) {
		return CommonResponse.getSuccessResponse(orgSchoolService.schoolPersonnelCount(query));
	}

	/**
	 * 学校校医配备核查情况 统计每个学校专职校医,兼职校医,专职保健老师,兼职保健老师人数
	 * 
	 * @param query
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("/doctor/count")
	@ResponseBody
	public Object SchoolDoctorStatistics(OrgSchoolQuery query) {
		return CommonResponse.getSuccessResponse(orgSchoolService.schoolDoctorCount(query));
	}

}

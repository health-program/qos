package com.paladin.qos.controller.school;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.common.core.container.ConstantsContainer;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.model.school.OrgSchoolPeople;
import com.paladin.qos.service.school.OrgSchoolNameService;
import com.paladin.qos.service.school.OrgSchoolPeopleService;
import com.paladin.qos.service.school.vo.OrgSchoolIdNameVO;
import com.paladin.qos.service.school.vo.OrgSchoolPeopleTreeVO;

/**
 * < 班级管理>
 * @author  MyKite
 * @version  [版本号, 2019年7月23日]
 */
@Controller
@RequestMapping("/qos/org/school/people")
public class OrgSchoolPeopleController {
	
	@Autowired
	private OrgSchoolPeopleService orgSchoolPeopleService;
	@Autowired
	private OrgSchoolNameService orgSchoolNameService;

	@RequestMapping(value = "/findList", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Object findListBySchoolId() {
		List<OrgSchoolPeople> list = orgSchoolPeopleService.findAll().stream()
				.sorted(Comparator.comparing(OrgSchoolPeople::getSchoolId)
						.thenComparing(OrgSchoolPeople::getSchoolSection)
						.thenComparing(OrgSchoolPeople::getGrade)
						.thenComparing(OrgSchoolPeople::getKlass))
				.collect(Collectors.toList());
		List<OrgSchoolPeopleTreeVO> result=new ArrayList<OrgSchoolPeopleTreeVO>();//学制
		
		
		Map<String,String> orgSchoolMap=new HashMap<String,String>();
		List<OrgSchoolIdNameVO> schoolNameList = orgSchoolNameService.schoolIdNameFind();
		schoolNameList.forEach(orgSchool->{
			orgSchoolMap.put(orgSchool.getId(), orgSchool.getName());
		});
		
		Map<String,String> schoolMap=new HashMap<String,String>();
		Map<String,String> schoolSectionMap=new HashMap<String,String>();
		Map<String,String> gradeMap=new HashMap<String,String>();
		Map<String,String> klassMap=new HashMap<String,String>();
		list.forEach(orgSchoolPeople->{
			//学校
			String schoolId = orgSchoolPeople.getSchoolId();
			OrgSchoolPeopleTreeVO schoolVo=new OrgSchoolPeopleTreeVO();
			if(schoolMap.get(schoolId)==null){
				schoolMap.put(schoolId, orgSchoolMap.get(schoolId));
				schoolVo.setId(schoolId);
				schoolVo.setName(orgSchoolMap.get(schoolId));
				schoolVo.setLevel("1");
				schoolVo.setParentId(null);
				result.add(schoolVo);
			}
			//学段
			Integer schoolSection = orgSchoolPeople.getSchoolSection();
			String schoolSectionValue = ConstantsContainer.getTypeValue("school-section-type", schoolSection.toString());
			if(schoolSectionMap.get(schoolId+"_"+schoolSection.toString())==null){
				schoolSectionMap.put(schoolId+"_"+schoolSection.toString(), schoolSectionValue);
				OrgSchoolPeopleTreeVO schoolSectionVo=new OrgSchoolPeopleTreeVO();
				schoolSectionVo.setId(schoolId+"_"+schoolSection.toString());
				schoolSectionVo.setName(schoolSectionValue);
				schoolSectionVo.setLevel("2");
				schoolSectionVo.setParentId(schoolId);
				result.add(schoolSectionVo);
			}
			//年级
			Integer grade = orgSchoolPeople.getGrade();
			String gradeValue = ConstantsContainer.getTypeValue("grade-type", grade.toString());
			if(gradeMap.get(schoolId+"_"+schoolSection+"_"+grade)==null){
				gradeMap.put(schoolId+"_"+schoolSection+"_"+grade, gradeValue);
				OrgSchoolPeopleTreeVO gradeVo=new OrgSchoolPeopleTreeVO();
				gradeVo.setId(schoolId+"_"+schoolSection+"_"+grade);
				gradeVo.setName(gradeValue);
				gradeVo.setLevel("3");
				gradeVo.setParentId(schoolId+"_"+schoolSection.toString());
				result.add(gradeVo);
			}
			//班级
			String klass = orgSchoolPeople.getKlass();
			klassMap.put(orgSchoolPeople.getId(), klass);
			OrgSchoolPeopleTreeVO klassVo=new OrgSchoolPeopleTreeVO();
			klassVo.setId(orgSchoolPeople.getId());
			klassVo.setName(klass);
			klassVo.setLevel("4");
			klassVo.setParentId(schoolId+"_"+schoolSection+"_"+grade);
			result.add(klassVo);
		});
        return CommonResponse.getSuccessResponse(result);
    }
	
}

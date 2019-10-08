package com.paladin.qos.controller.gongwei;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.paladin.qos.model.data.DataUnit;
import com.paladin.qos.service.data.DataUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.controller.analysis.AnalysisRequest;
import com.paladin.qos.service.analysis.AnalysisService;
import com.paladin.qos.service.analysis.data.DataCountUnit;
import com.paladin.qos.service.gongwei.vo.PhysicalManagementVO;

@Controller
@RequestMapping("/qos/gongwei/physical")
public class PhysicalManagementController {

	@Autowired
	private AnalysisService analysisService;

	@Autowired
	private DataUnitService dataUnitService;

	@GetMapping("/index")
	public Object dataIndex(Model model) {
		List<Integer> types=new ArrayList<>();
		types.add(DataUnit.TYPE_COMMUNITY);
		model.addAttribute("unit", dataUnitService.selectData(types));
		return "/qos/exhibition/physical_index";
	}

	@RequestMapping(value = "/search/all", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object searchAll(AnalysisRequest request) {

		List<PhysicalManagementVO> physicalManagementVOArrayList = new ArrayList<>();

		List<DataCountUnit> oldPeople = analysisService.countTotalNumByUnit("22003", 2, request.getStartTime(), request.getEndTime());
		List<DataCountUnit> physicalPeople = analysisService.countEventNumByUnit("22003", 2, request.getStartTime(), request.getEndTime());
		List<DataCountUnit> healthPeople = analysisService.countEventNumByUnit("22012", 2, request.getStartTime(), request.getEndTime());

		Map<String, Long> physicalPeopleMap = physicalPeople.stream().collect(Collectors.toMap(w -> w.getUnitId(), w -> w.getCount()));

		Map<String, Long> healthPeopleMap = healthPeople.stream().collect(Collectors.toMap(w -> w.getUnitId(), w -> w.getCount()));

		for (DataCountUnit oldPeopleData : oldPeople) {
			PhysicalManagementVO physicalManagementVO = new PhysicalManagementVO();
			String unitId = oldPeopleData.getUnitId();
			if (!StringUtils.isEmpty(unitId)) {
				physicalManagementVO.setUnitId(unitId);
				physicalManagementVO.setOldPeopleNumber(oldPeopleData.getCount());
				physicalManagementVO.setPhysicalNumber(physicalPeopleMap.get(unitId));
				physicalManagementVO.setCompletePhysicalNumber(healthPeopleMap.get(unitId));
				physicalManagementVOArrayList.add(physicalManagementVO);
			}
		}

		return CommonResponse.getSuccessResponse(physicalManagementVOArrayList);

	}

}

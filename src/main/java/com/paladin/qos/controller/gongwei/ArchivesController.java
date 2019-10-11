package com.paladin.qos.controller.gongwei;

import java.math.BigDecimal;
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
import com.paladin.qos.model.familydoctor.FamilyDoctorUnit;
import com.paladin.qos.service.analysis.AnalysisService;
import com.paladin.qos.service.analysis.data.DataCountUnit;
import com.paladin.qos.service.familydoctor.FamilyDoctorUnitService;
import com.paladin.qos.service.gongwei.vo.ArchivesManagementVO;

@Controller
@RequestMapping("/qos/gongwei/archives")
public class ArchivesController {

	@Autowired
	private AnalysisService analysisService;

	@Autowired
	private FamilyDoctorUnitService familyDoctorUnitService;

	@Autowired
	private DataUnitService dataUnitService;

	@GetMapping("/index")
	public Object dataIndex(Model model) {
		List<Integer> types=new ArrayList<>();
		types.add(DataUnit.TYPE_COMMUNITY);
		model.addAttribute("unit", dataUnitService.selectData(types));
		return "/qos/exhibition/archives_index";
	}

	@RequestMapping(value = "/search/all", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object searchAll(AnalysisRequest request) {

		List<ArchivesManagementVO> archivesManagementVOList = new ArrayList<>();

		List<DataCountUnit> totalArchives = analysisService.countTotalNumByUnit("22001", 2, request.getStartTime(), request.getEndTime());
		List<DataCountUnit> eventArchives = analysisService.countEventNumByUnit("22002", 2, request.getStartTime(), request.getEndTime());
		List<FamilyDoctorUnit> familyDoctorUnits = familyDoctorUnitService.findAll();

		Map<String, Long> eventArchivesMap = eventArchives.stream().collect(Collectors.toMap(w -> w.getUnitId(), w -> w.getCount()));
		Map<String, Long> totalArchivesMap = totalArchives.stream().collect(Collectors.toMap(w -> w.getUnitId(), w -> w.getCount()));
//		Map<String, Long> familyDoctorUnitsMap = familyDoctorUnits.stream()
//				.collect(Collectors.toMap(w -> w.getId(), w -> (long) (Double.parseDouble(w.getPopulation()) * 10000)));

		for (FamilyDoctorUnit familyDoctorUnit : familyDoctorUnits) {
			ArchivesManagementVO archivesManagementVO = new ArchivesManagementVO();
			String unitId = familyDoctorUnit.getId();
			if (!StringUtils.isEmpty(unitId)) {
				archivesManagementVO.setUnitId(unitId);
				archivesManagementVO.setActiveArchivesNumber(totalArchivesMap.get(unitId));
				archivesManagementVO.setPublicArchivesNumber(eventArchivesMap.get(unitId));
				BigDecimal value1 = new BigDecimal(familyDoctorUnit.getPopulation());
				BigDecimal value2 = new BigDecimal("10000");
				archivesManagementVO.setPeopleNumber((long) value1.multiply(value2).doubleValue());
				archivesManagementVOList.add(archivesManagementVO);
			}
		}

		return CommonResponse.getSuccessResponse(archivesManagementVOList);
	}

}

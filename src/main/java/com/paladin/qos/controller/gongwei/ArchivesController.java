package com.paladin.qos.controller.gongwei;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import com.paladin.qos.model.data.DataUnit;
import com.paladin.qos.service.analysis.AnalysisConstant;
import com.paladin.qos.service.data.DataUnitService;
import com.paladin.qos.service.data.vo.DataUnitVO;
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
//		List<Integer> types=new ArrayList<>();
//		types.add(DataUnit.TYPE_COMMUNITY);
//		List<DataUnitVO> dataUnitVOList =dataUnitService.selectData(types);
//		Iterator<DataUnitVO> it=dataUnitVOList.iterator();
//		while(it.hasNext()){
//			DataUnitVO dataUnitVO=it.next();
//			if(StringUtils.equalsIgnoreCase("320583810343",dataUnitVO.getId())){
//				it.remove();
//			}
//		}
//		model.addAttribute("unit", dataUnitVOList);
        return "/qos/exhibition/archives_index";
    }

    @RequestMapping(value = "/search/archives/month", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object searchArchivesByMonth() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);
        Date startDate=c.getTime();

        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date endDate=ca.getTime();


        List<DataCountUnit> totalArchives = analysisService.countTotalNumByUnit("22001", 2, startDate, endDate, AnalysisConstant.SPECIAL_UNITS_FUYOU);
        List<ArchivesManagementVO> archivesManagementVOList = new ArrayList<>();
        for(DataCountUnit dataCountUnit:totalArchives){
            ArchivesManagementVO archivesManagementVO=new ArchivesManagementVO();
            archivesManagementVO.setUnitId(dataCountUnit.getUnitId());
            archivesManagementVO.setActiveArchivesNumber(dataCountUnit.getCount());
            archivesManagementVOList.add(archivesManagementVO);
        }
        return CommonResponse.getSuccessResponse(archivesManagementVOList);
    }


    @RequestMapping(value = "/search/all", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object searchAll(AnalysisRequest request) {

        List<ArchivesManagementVO> archivesManagementVOList = new ArrayList<>();
        List<DataCountUnit> totalArchives = analysisService.countTotalNumByUnit("22001", 2, request.getStartTime(), request.getEndTime(), AnalysisConstant.SPECIAL_UNITS_FUYOU);
        List<DataCountUnit> eventArchives = analysisService.countEventNumByUnit("22002", 2, request.getStartTime(), request.getEndTime(), AnalysisConstant.SPECIAL_UNITS_FUYOU);
        List<FamilyDoctorUnit> familyDoctorUnits = familyDoctorUnitService.findAll();
//		if (!StringUtils.isEmpty(request.getUnitId())){
//			Iterator<FamilyDoctorUnit> it = familyDoctorUnits.iterator();
//			while(it.hasNext()){
//				FamilyDoctorUnit d = it.next();
//				if(!StringUtils.equalsIgnoreCase(d.getId(),request.getUnitId())){
//					it.remove();
//				}
//			}
//		}
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

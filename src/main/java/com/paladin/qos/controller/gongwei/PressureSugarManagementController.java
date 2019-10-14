package com.paladin.qos.controller.gongwei;


import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.analysis.DataConstantContainer;
import com.paladin.qos.analysis.DataConstantContainer.*;
import com.paladin.qos.controller.analysis.AnalysisRequest;
import com.paladin.qos.model.data.DataUnit;
import com.paladin.qos.service.analysis.AnalysisConstant;
import com.paladin.qos.service.analysis.AnalysisService;
import com.paladin.qos.service.analysis.data.DataCountUnit;
import com.paladin.qos.service.data.DataUnitService;
import com.paladin.qos.service.data.vo.DataUnitVO;
import com.paladin.qos.service.gongwei.PressureSugarManagementService;
import com.paladin.qos.service.gongwei.vo.PressureSugarManagementVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/qos/gongwei/pressureSugar")
public class PressureSugarManagementController {

    @Autowired
    private DataUnitService dataUnitService;

    @Autowired
    private AnalysisService analysisService;

    @Autowired
    private PressureSugarManagementService pressureSugarManagementService;

    @GetMapping("/index")
    public Object dataIndex(Model model) {
//        List<Integer> types=new ArrayList<>();
//        types.add(DataUnit.TYPE_COMMUNITY);
//
//        List<DataUnitVO> dataUnitVOList =dataUnitService.selectData(types);
//        Iterator<DataUnitVO> it=dataUnitVOList.iterator();
//        while(it.hasNext()){
//            DataUnitVO dataUnitVO=it.next();
//            if(StringUtils.equalsIgnoreCase("320583810343",dataUnitVO.getId())){
//                it.remove();
//            }
//        }
//        model.addAttribute("unit", dataUnitVOList);
        return "/qos/exhibition/pressureSugar_index";
    }

    @RequestMapping(value = "/search/all", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object searchAll(AnalysisRequest request) {
        List<PressureSugarManagementVO> pressureSugarManagementVOList = new ArrayList<>();
        List<DataCountUnit> bloodTotalList = analysisService.countTotalNumByUnit("22004", 2, request.getStartTime(), request.getEndTime(), AnalysisConstant.SPECIAL_UNITS_FUYOU);
//        if (!StringUtils.isEmpty(request.getUnitId())){
//            Iterator<DataCountUnit> it = bloodTotalList.iterator();
//            while(it.hasNext()){
//                DataCountUnit d = it.next();
//                if(!StringUtils.equalsIgnoreCase(d.getUnitId(),request.getUnitId())){
//                    it.remove();
//                }
//            }
//        }
        List<DataCountUnit> bloodEventList = analysisService.countEventNumByUnit("22004", 2, request.getStartTime(), request.getEndTime(),AnalysisConstant.SPECIAL_UNITS_FUYOU);
        Map<String, Long> bloodEventMap = bloodEventList.stream().collect(Collectors.toMap(w -> w.getUnitId(), w -> w.getCount()));
        List<DataCountUnit> diabetesTotalList = analysisService.countTotalNumByUnit("22006", 2, request.getStartTime(), request.getEndTime(),AnalysisConstant.SPECIAL_UNITS_FUYOU);
        Map<String, Long> diabetesTotalMap = diabetesTotalList.stream().collect(Collectors.toMap(w -> w.getUnitId(), w -> w.getCount()));
        List<DataCountUnit> diabetesEventList = analysisService.countEventNumByUnit("22006", 2, request.getStartTime(), request.getEndTime(),AnalysisConstant.SPECIAL_UNITS_FUYOU);
        Map<String, Long> diabetesEventMap = diabetesEventList.stream().collect(Collectors.toMap(w -> w.getUnitId(), w -> w.getCount()));


        for (DataCountUnit data:bloodTotalList){
            PressureSugarManagementVO pressureSugarManagementVO=new PressureSugarManagementVO();
            pressureSugarManagementVO.setUnitId(data.getUnitId());
            pressureSugarManagementVO.setPressureNumber(data.getCount());
            pressureSugarManagementVO.setPressureRecentReach(bloodEventMap.get(data.getUnitId()));
            pressureSugarManagementVO.setSugarNumber(diabetesTotalMap.get(data.getUnitId()));
            pressureSugarManagementVO.setSugarRecentReach(diabetesEventMap.get(data.getUnitId()));
            pressureSugarManagementService.mergeData(getMappingUnitId(data.getUnitId()),null,request.getEndTime(),pressureSugarManagementVO);
            pressureSugarManagementVOList.add(pressureSugarManagementVO);
        }

        return CommonResponse.getSuccessResponse(pressureSugarManagementVOList);
    }


    private String getMappingUnitId(String unitId) {
        List<DataUnit> dataUnitList=new ArrayList<>();
        dataUnitList=dataUnitService.findAll();
        for (DataUnit dataUnit:dataUnitList){
            if (StringUtils.equalsIgnoreCase(dataUnit.getId(),unitId))
                return dataUnit.getGongweiCode();
        }
        return null;
    }

}

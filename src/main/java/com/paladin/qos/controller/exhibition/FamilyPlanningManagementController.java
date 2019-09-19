package com.paladin.qos.controller.exhibition;

import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.analysis.impl.fuyou.jhsy.*;
import com.paladin.qos.controller.analysis.AnalysisRequest;
import com.paladin.qos.service.analysis.AnalysisService;
import com.paladin.qos.service.analysis.data.DataPointMonth;
import com.paladin.qos.service.analysis.data.DataResult;
import com.paladin.qos.service.exhibition.FamilyPlanningManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <计划生育管理>
 *
 * @author Huangguochen
 * @create 2019/8/26 13:38
 */
@Controller
@RequestMapping("/qos/exhibition/family")
public class FamilyPlanningManagementController {

    @Autowired
    private FamilyPlanningManagementService familyPlanningManagementService;

    @Autowired
    private AnalysisService analysisService;

    @GetMapping("/index")
    public Object processIndex() {
        return "/qos/exhibition/family_planning_index";
    }

    @PostMapping("/search/bycs")
    @ResponseBody
    public  Object searchBycs(AnalysisRequest request) {
        List<String> ids = Arrays.asList(BirthControlPills.EVENT_ID, CondomDistribution.EVENT_ID);
        List<DataResult<DataPointMonth>> list = new ArrayList<>(2);
        DataResult<DataPointMonth> set;
        for (String id : ids) {
            set = analysisService.getDataSetOfMonth(id, request.getStartTime(), request.getEndTime());
            list.add(set);
        }
        return CommonResponse.getSuccessResponse(list);

       /* return CommonResponse.getSuccessResponse(familyPlanningManagementService.getContraceptivemeasuresTotal(request));*/
    }

    @PostMapping("/search/gnjy")
    @ResponseBody
    public  Object searchGnjy(AnalysisRequest request) {
        List<String> ids = Arrays.asList(IntrauterineDeviceRemoval.EVENT_ID, IntrauterineDevicePlacement.EVENT_ID);
        List<DataResult<DataPointMonth>> list = new ArrayList<>(2);
        DataResult<DataPointMonth> set;
        for (String id : ids) {
            set = analysisService.getDataSetOfMonth(id, request.getStartTime(), request.getEndTime());
            list.add(set);
        }
        return CommonResponse.getSuccessResponse(list);
      /*  return CommonResponse.getSuccessResponse(familyPlanningManagementService.getIntrauterineDeviceTotal(request));*/
    }

    @PostMapping("/search/ywlc")
    @ResponseBody
    public  Object searchYwlc(AnalysisRequest request) {
        return CommonResponse.getSuccessResponse(analysisService.getDataSetOfMonth(MedicalAbortionTotal.EVENT_ID, request.getStartTime(), request.getEndTime()));
    }

    @PostMapping("/search/fyxg")
    @ResponseBody
    public  Object searchFyxg(AnalysisRequest request) {
        return CommonResponse.getSuccessResponse(analysisService.getDataSetOfMonth(NegativePressureSuction.EVENT_ID, request.getStartTime(), request.getEndTime()));
    }
}

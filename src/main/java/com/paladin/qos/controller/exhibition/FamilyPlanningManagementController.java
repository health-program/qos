package com.paladin.qos.controller.exhibition;

import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.controller.analysis.AnalysisRequest;
import com.paladin.qos.service.exhibition.FamilyPlanningManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("/index")
    public Object processIndex() {
        return "/qos/exhibition/family_planning_index";
    }

    @PostMapping("/search/bycs")
    @ResponseBody
    public  Object searchBycs(AnalysisRequest request) {
        return CommonResponse.getSuccessResponse(familyPlanningManagementService.getContraceptivemeasuresTotal(request));
    }

    @PostMapping("/search/gnjy")
    @ResponseBody
    public  Object searchGnjy(AnalysisRequest request) {
        return CommonResponse.getSuccessResponse(familyPlanningManagementService.getIntrauterineDeviceTotal(request));
    }

    @PostMapping("/search/ywlc")
    @ResponseBody
    public  Object searchYwlc(AnalysisRequest request) {
        return CommonResponse.getSuccessResponse(familyPlanningManagementService.getMedicalAbortionTotal(request.getStartTime(),request.getEndTime()));
    }

    @PostMapping("/search/fyxg")
    @ResponseBody
    public  Object searchFyxg(AnalysisRequest request) {
        return CommonResponse.getSuccessResponse(familyPlanningManagementService.getNegativePressureSuctionTotal(request.getStartTime(),request.getEndTime()));
    }
}

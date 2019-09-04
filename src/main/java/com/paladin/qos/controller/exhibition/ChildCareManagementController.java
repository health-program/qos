package com.paladin.qos.controller.exhibition;

import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.controller.analysis.AnalysisRequest;
import com.paladin.qos.service.exhibition.ChildCareManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <儿童保健管理>
 *
 * @author Huangguochen
 * @create 2019/8/26 8:53
 */
@Controller
@RequestMapping("/qos/exhibition/child")
public class ChildCareManagementController {

    @Autowired
    private ChildCareManagementService childCareManagementService;

    @GetMapping("/index")
    public Object processIndex() {
        return "/qos/exhibition/child_care_index";
    }

    @PostMapping("/search/xsrfm")
    @ResponseBody
    public  Object searchXsrfm(AnalysisRequest request) {
        return CommonResponse.getSuccessResponse(childCareManagementService.getNewbornChildbirthTotal(request));
    }

    @PostMapping("/search/all")
    @ResponseBody
    public  Object searchAll(AnalysisRequest request) {
        return CommonResponse.getSuccessResponse(childCareManagementService.searchAllNumberData(request));
    }

    @RequestMapping(value = "/search/xzb", method = { RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public  Object searchXzb(AnalysisRequest request) {
        return CommonResponse.getSuccessResponse(childCareManagementService.getInfantCongenitalHeartDisease(request.getStartTime(),request.getEndTime()));
    }

    @RequestMapping(value = "/search/ettj", method = { RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public  Object searchEttj(AnalysisRequest request) {
        return CommonResponse.getSuccessResponse(childCareManagementService.getChildHealthCheckup(request.getStartTime(),request.getEndTime()));
    }

    @RequestMapping(value = "/search/yesl", method = { RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public  Object searchYesl(AnalysisRequest request) {
        return CommonResponse.getSuccessResponse(childCareManagementService.getInfantVisionTotal(request.getStartTime(),request.getEndTime()));
    }

}

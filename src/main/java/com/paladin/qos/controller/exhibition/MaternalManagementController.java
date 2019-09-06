package com.paladin.qos.controller.exhibition;

import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.controller.analysis.AnalysisRequest;
import com.paladin.qos.service.exhibition.MaternalManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <孕产妇管理>
 * @author Huangguochen
 * @create 2019/8/22 14:12
 */

@Controller
@RequestMapping("/qos/exhibition/maternal")
public class MaternalManagementController {

    @Autowired
    private MaternalManagementService maternalManagementService;

    @GetMapping("/index")
    public Object processIndex() {
        return "/qos/exhibition/maternal_index";
    }

    @PostMapping("/search/all")
    @ResponseBody
    public  Object searchAll(AnalysisRequest request) {
        return CommonResponse.getSuccessResponse(maternalManagementService.searchAllNumberData(request));
    }

    @PostMapping("/search/hqjc")
    @ResponseBody
    public  Object searchHqjc(AnalysisRequest request) {
        return CommonResponse.getSuccessResponse(maternalManagementService.searchPremaritalCheckTotal(request));
    }

    @PostMapping("/search/yfsc")
    @ResponseBody
    public  Object searchYfsc(AnalysisRequest request) {
        return CommonResponse.getSuccessResponse(maternalManagementService.getMaternalScreeningTotal(request.getStartTime(),request.getEndTime()));
    }

    @PostMapping("/search/fnb")
    @ResponseBody
    public  Object searchFnb(AnalysisRequest request) {
        return CommonResponse.getSuccessResponse(maternalManagementService.getWomenDiseaseScreeningTotal(request.getStartTime(),request.getEndTime()));
    }

    @RequestMapping(value = "/search/yftj", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public  Object searchYftj(AnalysisRequest request) {
        return CommonResponse.getSuccessResponse(maternalManagementService.getMaternalCheckupData(request.getStartTime(),request.getEndTime()));
    }

}

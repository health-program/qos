package com.paladin.qos.controller.exhibition;

import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.analysis.impl.fuyou.etbj.*;
import com.paladin.qos.analysis.impl.fuyou.ycf.NewbornVisit;
import com.paladin.qos.controller.analysis.AnalysisRequest;
import com.paladin.qos.service.analysis.AnalysisService;
import com.paladin.qos.service.analysis.data.DataCountUnit;
import com.paladin.qos.service.analysis.data.DataPointMonth;
import com.paladin.qos.service.analysis.data.DataResult;
import com.paladin.qos.service.exhibition.ChildCareManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
    private AnalysisService analysisService;

    @Autowired
    private ChildCareManagementService childCareManagementService;

    @GetMapping("/index")
    public Object processIndex() {
        return "/qos/exhibition/child_care_index";
    }

    @GetMapping("/org/index")
    public Object processOrgIndex() {
        return "/qos/exhibition/child_care_org_index";
    }

    @PostMapping("/search/xsrfm")
    @ResponseBody
    public  Object searchXsrfm(AnalysisRequest request) {
        List<String> ids = Arrays.asList(FemaleNewbornChildbirth.EVENT_ID, MaleNewbornChildbirth.EVENT_ID, NewbornHearing.EVENT_ID);
        List<DataResult<DataPointMonth>> list = new ArrayList<>(3);
        DataResult<DataPointMonth> set;
        for (String id : ids) {
            set = analysisService.getDataSetOfMonth(id, request.getStartTime(), request.getEndTime());
            list.add(set);
        }
        return CommonResponse.getSuccessResponse(list);
      /*  return CommonResponse.getSuccessResponse(childCareManagementService.getNewbornChildbirthTotal(request));*/
    }

    @PostMapping("/search/all")
    @ResponseBody
    public  Object searchAll(AnalysisRequest request) {
        List<String> ids = Arrays.asList(ChildCard.EVENT_ID, NeonatalDiseaseScreening.EVENT_ID, NeonatalBirthDefects.EVENT_ID, ChildHealthCheck.EVENT_ID, InfantDeath.EVENT_ID);
        List<DataResult<DataPointMonth>> list = new ArrayList<>(5);
        DataResult<DataPointMonth> set;
        for (String id : ids) {
            set = analysisService.getDataSetOfMonth(id, request.getStartTime(), request.getEndTime());
            list.add(set);
        }
        return CommonResponse.getSuccessResponse(list);
       /* return CommonResponse.getSuccessResponse(childCareManagementService.searchAllNumberData(request));*/
    }

    @RequestMapping(value = "/search/xzb", method = { RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public  Object searchXzb(AnalysisRequest request) {
        return CommonResponse.getSuccessResponse(analysisService.countTotalNumByUnit(InfantCongenitalHeartDisease.EVENT_ID, request.getStartTime(), request.getEndTime()));
    }

    @RequestMapping(value = "/search/ettj", method = { RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public  Object searchEttj(AnalysisRequest request) {
        return CommonResponse.getSuccessResponse(analysisService.countTotalNumByUnit(ChildHealthCheckup.EVENT_ID, request.getStartTime(), request.getEndTime()));
    }

    @RequestMapping(value = "/search/yesl", method = { RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public  Object searchYesl(AnalysisRequest request) {
        return CommonResponse.getSuccessResponse(analysisService.countTotalNumByUnit(InfantVision.EVENT_ID, request.getStartTime(), request.getEndTime()));
    }


    @RequestMapping(value = "/search/org", method = { RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public  Object searchOrg(AnalysisRequest request) {
        List<DataCountUnit> visit = analysisService.countTotalNumByUnit(NewbornVisit.EVENT_ID, request.getStartTime(), request.getEndTime());
        List<DataCountUnit> male = analysisService.countTotalNumByUnit(MaleNewbornChildbirth.EVENT_ID, request.getStartTime(), request.getEndTime());
        List<DataCountUnit> female = analysisService.countTotalNumByUnit(FemaleNewbornChildbirth.EVENT_ID, request.getStartTime(), request.getEndTime());
        List<DataCountUnit> card = analysisService.countTotalNumByUnit(ChildCard.EVENT_ID, request.getStartTime(), request.getEndTime());
        HashMap<String, List<DataCountUnit>> map = new HashMap<>(4);
        map.put(NewbornVisit.EVENT_ID, visit);
        map.put(MaleNewbornChildbirth.EVENT_ID, male);
        map.put(FemaleNewbornChildbirth.EVENT_ID, female);
        map.put(ChildCard.EVENT_ID, card);
        return CommonResponse.getSuccessResponse(map);
//        return CommonResponse.getSuccessResponse(childCareManagementService.getChildCareDataByOrg(request.getStartTime(),request.getEndTime()));
    }

}


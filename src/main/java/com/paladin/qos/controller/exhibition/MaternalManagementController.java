package com.paladin.qos.controller.exhibition;

import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.analysis.impl.fuyou.ycf.*;
import com.paladin.qos.controller.analysis.AnalysisRequest;
import com.paladin.qos.service.analysis.AnalysisService;
import com.paladin.qos.service.analysis.data.DataCountUnit;
import com.paladin.qos.service.analysis.data.DataPointMonth;
import com.paladin.qos.service.analysis.data.DataResult;
import com.paladin.qos.service.exhibition.MaternalManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * <孕产妇管理>
 * @author Huangguochen
 * @create 2019/8/22 14:12
 */

@Controller
@RequestMapping("/qos/exhibition/maternal")
public class MaternalManagementController {

    @Autowired
    private AnalysisService analysisService;

    @Autowired
    private MaternalManagementService maternalManagementService;

    @GetMapping("/index")
    public Object processIndex() {
        return "/qos/exhibition/maternal_index";
    }

    @GetMapping("/org/index")
    public Object processOrgIndex() {
        return "/qos/exhibition/maternal_org_index";
    }

    @PostMapping("/search/left")
    @ResponseBody
    public  Object searchLeft(AnalysisRequest request) {
        List<String> ids = Arrays.asList(HighriskMaternal.EVENT_ID, PrepregnancyCheck.EVENT_ID, MaternalVisit.EVENT_ID, NewbornVisit.EVENT_ID
                , MaternalDeath.EVENT_ID);
        List<DataResult<DataPointMonth>> list = new ArrayList<>(5);
        DataResult<DataPointMonth> set;
        for (String id : ids) {
            set = analysisService.getDataSetOfMonth(id, request.getStartTime(), request.getEndTime());
            list.add(set);
        }
        return CommonResponse.getSuccessResponse(list);
    }


    @PostMapping("/search/up")
    @ResponseBody
    public  Object searchRightUp(AnalysisRequest request) {
        List<String> ids = Arrays.asList(NumberOfFolates.EVENT_ID, FolicAcidDispensingBottle.EVENT_ID, CervicalCancerScreening.EVENT_ID, BreastCancerScreening.EVENT_ID);
        List<DataResult<DataPointMonth>> list = new ArrayList<>(4);
        DataResult<DataPointMonth> set;
        for (String id : ids) {
            set = analysisService.getDataSetOfMonth(id, request.getStartTime(), request.getEndTime());
            list.add(set);
        }
        return CommonResponse.getSuccessResponse(list);
    }

    @PostMapping("/search/down")
    @ResponseBody
    public  Object searchRightDown(AnalysisRequest request) {
        List<String> ids = Arrays.asList(SyphilisTest.EVENT_ID, SyphilisInfection.EVENT_ID, HepatitisBtest.EVENT_ID, HepatitisBdeterminesInfection.EVENT_ID);
        List<DataResult<DataPointMonth>> list = new ArrayList<>(4);
        DataResult<DataPointMonth> set;
        for (String id : ids) {
            set = analysisService.getDataSetOfMonth(id, request.getStartTime(), request.getEndTime());
            list.add(set);
        }
        return CommonResponse.getSuccessResponse(list);
    }

    @PostMapping("/search/hqjc")
    @ResponseBody
    public  Object searchHqjc(AnalysisRequest request) {
        List<String> ids = Arrays.asList(MalePremaritalCheck.EVENT_ID, FemalePremaritalCheck.EVENT_ID);
        List<DataResult<DataPointMonth>> list = new ArrayList<>(2);
        DataResult<DataPointMonth> set;
        for (String id : ids) {
            set = analysisService.getDataSetOfMonth(id, request.getStartTime(), request.getEndTime());
            list.add(set);
        }
        return CommonResponse.getSuccessResponse(list);
      /*  return CommonResponse.getSuccessResponse(maternalManagementService.searchPremaritalCheckTotal(request));*/
    }

    @PostMapping("/search/yfsc")
    @ResponseBody
    public  Object searchYfsc(AnalysisRequest request) {
        return CommonResponse.getSuccessResponse(analysisService.getDataSetOfMonth(MaternalScreening.EVENT_ID, request.getStartTime(), request.getEndTime()));
    }

    @PostMapping("/search/fnb")
    @ResponseBody
    public  Object searchFnb(AnalysisRequest request) {
        return CommonResponse.getSuccessResponse(analysisService.getDataSetOfMonth(WomenDiseaseScreening.EVENT_ID, request.getStartTime(), request.getEndTime()));
    }

    @RequestMapping(value = "/search/yftj", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public  Object searchYftj(AnalysisRequest request) {
        List<DataCountUnit> first = analysisService.countTotalNumByUnit(FirstExaminationTimes.EVENT_ID, request.getStartTime(), request.getEndTime());
        List<DataCountUnit> cycle = analysisService.countTotalNumByUnit(CycleExaminationTimes.EVENT_ID, request.getStartTime(), request.getEndTime());
        HashMap<String, List<DataCountUnit>> map = new HashMap<>(2);
        map.put(FirstExaminationTimes.EVENT_ID, first);
        map.put(CycleExaminationTimes.EVENT_ID, cycle);
        return CommonResponse.getSuccessResponse(map);
      /*  return CommonResponse.getSuccessResponse(maternalManagementService.getMaternalCheckupData(request.getStartTime(),request.getEndTime()));*/
    }

    @RequestMapping(value = "/search/yfjk", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public  Object searchYfjk(AnalysisRequest request) {
        List<String> ids = Arrays.asList(PregnantWomenBuildCard.EVENT_ID, EarlyPregnancyCard.EVENT_ID);
        List<DataResult<DataPointMonth>> list = new ArrayList<>(2);
        DataResult<DataPointMonth> set;
        for (String id : ids) {
            set = analysisService.getDataSetOfMonth(id, request.getStartTime(), request.getEndTime());
            list.add(set);
        }
        return CommonResponse.getSuccessResponse(list);
       /* return CommonResponse.getSuccessResponse(maternalManagementService.getBuildCardNumber(request.getStartTime(),request.getEndTime()));*/
    }

    @RequestMapping(value = "/search/wcq", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public  Object searchWcq() {
        return CommonResponse.getSuccessResponse(maternalManagementService.getWcqData());
    }



    @RequestMapping(value = "/search/org", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public  Object searchOrg(AnalysisRequest request) {
        List<DataCountUnit> early = analysisService.countTotalNumByUnit(EarlyPregnancyCard.EVENT_ID, request.getStartTime(), request.getEndTime());
        List<DataCountUnit> pregnant = analysisService.countTotalNumByUnit(PregnantWomenBuildCard.EVENT_ID, request.getStartTime(), request.getEndTime());
        List<DataCountUnit> maternal = analysisService.countTotalNumByUnit(MaternalVisit.EVENT_ID, request.getStartTime(), request.getEndTime());
        HashMap<String, List<DataCountUnit>> map = new HashMap<>(3);
        map.put(EarlyPregnancyCard.EVENT_ID, early);
        map.put(PregnantWomenBuildCard.EVENT_ID, pregnant);
        map.put(MaternalVisit.EVENT_ID, maternal);
        return CommonResponse.getSuccessResponse(map);
        /*return CommonResponse.getSuccessResponse(maternalManagementService.getMaternalDataByOrg(request.getStartTime(),request.getEndTime()));*/
    }

}

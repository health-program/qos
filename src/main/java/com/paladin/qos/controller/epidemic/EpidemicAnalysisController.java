package com.paladin.qos.controller.epidemic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.service.epidemic.EpidemicAnalysisService;
import com.paladin.qos.service.epidemic.dto.EpidemicAnalysisQueryDTO;

/**
 * @author MyKite
 * @version 2019年12月10日 下午3:05:52
 */
@Controller
@RequestMapping("/qos/org/school/analysis")
public class EpidemicAnalysisController extends ControllerSupport {

    @Autowired
    private EpidemicAnalysisService epidemicAnalysisService;
    
    /**
     * 疫情量
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/quantity/index")
    public String quantityIndex() {
	return "/qos/epidemic/analysis/epidemic_quantity_index";
    }

    @RequestMapping("/quantity")
    @ResponseBody
    public Object schoolPersonnelStatistics(EpidemicAnalysisQueryDTO query) {
	return CommonResponse.getSuccessResponse(epidemicAnalysisService.schoolepidemicquantity(query));
    }

    @RequestMapping("/quantity/rate")
    @ResponseBody
    public Object schoolepidemicRate(EpidemicAnalysisQueryDTO query) {
	return CommonResponse.getSuccessResponse(epidemicAnalysisService.schoolepidemicRate(query));
    }
    
    /**
     * 人数
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/number/index")
    public String numberIndex() {
	return "/qos/epidemic/analysis/epidemic_number_index";
    }
    
    @RequestMapping("/number")
    @ResponseBody
    public Object schoolPersonnelnumber(EpidemicAnalysisQueryDTO query) {
	return CommonResponse.getSuccessResponse(epidemicAnalysisService.schoolepidemicnumber(query));
    }

    @RequestMapping("/number/rate")
    @ResponseBody
    public Object schoolepidemicnumberRate(EpidemicAnalysisQueryDTO query) {
	return CommonResponse.getSuccessResponse(epidemicAnalysisService.schoolepidemicnumberRate(query));
    }
    
    /**
     * 起均
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/start/number/index")
    public String startNumberIndex() {
	return "/qos/epidemic/analysis/epidemic_start_number_index";
    }
    
    @RequestMapping("/start/number")
    @ResponseBody
    public Object schoolPersonnelStartNumber(EpidemicAnalysisQueryDTO query) {
	return CommonResponse.getSuccessResponse(epidemicAnalysisService.schoolPersonnelStartNumber(query));
    }
    
    /**
     * 人群分布
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/crowd/index")
    public String crowdIndex() {
	return "/qos/epidemic/analysis/epidemic_crowd_index";
    }
    
    @RequestMapping("/crowd")
    @ResponseBody
    public Object schoolPersonnelcrowd(EpidemicAnalysisQueryDTO query) {
	return CommonResponse.getSuccessResponse(epidemicAnalysisService.schoolPersonnelcrowd(query));
    }
    
    /**
     * 区域疫情量构成比
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/region/index")
    public String regionIndex() {
	return "/qos/epidemic/analysis/epidemic_region_index";
    }
    
    @RequestMapping("/region")
    @ResponseBody
    public Object schoolPersonnelRegion(EpidemicAnalysisQueryDTO query) {
	return CommonResponse.getSuccessResponse(epidemicAnalysisService.schoolPersonnelRegion(query));
    }
    
    /**
     * 区域涉及人数构成比
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/region/num/index")
    public String regionIndex1() {
	return "/qos/epidemic/analysis/epidemic_region_num_index";
    }
    
    @RequestMapping("/region/num")
    @ResponseBody
    public Object schoolPersonnelRegion1(EpidemicAnalysisQueryDTO query) {
	return CommonResponse.getSuccessResponse(epidemicAnalysisService.schoolPersonnelRegion1(query));
    }
    
    @RequestMapping("/region/num/details")
    @ResponseBody
    public Object schoolPersonnelRegion1details(EpidemicAnalysisQueryDTO query) {
	return CommonResponse.getSuccessResponse(epidemicAnalysisService.schoolPersonnelRegion1details(query));
    }
    
    /**
     * 区域起均人数
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/region/start/num/index")
    public String startNumIndex() {
	return "/qos/epidemic/analysis/epidemic_region_start_num_index";
    }
    
    @RequestMapping("/region/start/num")
    @ResponseBody
    public Object schoolPersonnelstartNum(EpidemicAnalysisQueryDTO query) {
	return CommonResponse.getSuccessResponse(epidemicAnalysisService.schoolPersonnelstartNum(query));
    }
    
    /**
     * 区域罹患率
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/region/incidence/index")
    public String incidenceIndex() {
	return "/qos/epidemic/analysis/epidemic_region_incidence_index";
    }
    
    @RequestMapping("/region/incidence")
    @ResponseBody
    public Object schoolPersonnelincidence(EpidemicAnalysisQueryDTO query) {
	return CommonResponse.getSuccessResponse(epidemicAnalysisService.schoolPersonnelincidence(query));
    }
    
    
    
    /**
     * 警戒值分析-疾病分类
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/surpass/disease/index")
    public String diseaseIndex() {
	return "/qos/epidemic/analysis/epidemic_surpass_disease_index";
    }
    
    @RequestMapping("/surpass/disease")
    @ResponseBody
    public Object schoolPersonneldisease(EpidemicAnalysisQueryDTO query) {
	return CommonResponse.getSuccessResponse(epidemicAnalysisService.schoolPersonneldisease(query));
    }
    
    /**
     * 警戒值分析-区镇分布
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/surpass/region/index")
    public String surpassRegionIndex(Model model) {
	return "/qos/epidemic/analysis/epidemic_surpass_region_index";
    }
    
    @RequestMapping("/surpass/region")
    @ResponseBody
    public Object schoolPersonnelSurpassRegion(EpidemicAnalysisQueryDTO query) {
	return CommonResponse.getSuccessResponse(epidemicAnalysisService.schoolPersonnelSurpassRegion(query));
    }
}

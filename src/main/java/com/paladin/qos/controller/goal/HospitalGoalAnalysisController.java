package com.paladin.qos.controller.goal;


import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.model.data.DataUnit;
import com.paladin.qos.model.goal.HospitalAnnualGoal;
import com.paladin.qos.service.data.DataUnitService;
import com.paladin.qos.service.goal.HospitalAnnualGoalService;
import com.paladin.qos.service.goal.HospitalMonthGoalService;
import com.paladin.qos.service.goal.dto.HospitalGoalAnalysisQuery;
import com.paladin.qos.service.goal.vo.HospitalAnnualGoalVO;
import com.paladin.qos.service.goal.vo.HospitalMonthGoalVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * 医院年度指标
 */
@Controller
@RequestMapping("/qos/hospital/goal/analysis")
public class HospitalGoalAnalysisController extends ControllerSupport {

    @Autowired
    private HospitalAnnualGoalService hospitalAnnualGoalService;
    @Autowired
    private HospitalMonthGoalService hospitalMonthGoalService;
    @Autowired
    private DataUnitService dataUnitService;

    @GetMapping("/index")
    @QueryInputMethod(queryClass = HospitalGoalAnalysisQuery.class)
    public String index(Model model) {
        List<Integer> types=new ArrayList<>();
        types.add(DataUnit.TYPE_HOSPITAL);
        model.addAttribute("unit", dataUnitService.selectData(types));
        return "/qos/goal/goal_analysis_index";
    }

    @RequestMapping(value = "/goalList", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    @QueryOutputMethod(queryClass = HospitalGoalAnalysisQuery.class, paramIndex = 0)
    public Object findPage(HospitalGoalAnalysisQuery query) {
        String year_month=query.getYear();
        String[] date = year_month.split("-");
        String year=date[0];
        String month=date[1];
        String yearmonth=year+"-"+month;
        String lastYear=String.valueOf(Long.parseLong(year)-1);
        String lastBeforeYear=String.valueOf(Long.parseLong(year)-2);
        String lastYearmonth=lastYear+"-"+month;
        //查询年度目标
        Map<String,Object> result=new HashMap<>();
        query.setYear(year);
        List<HospitalAnnualGoalVO> annualGoalList=hospitalAnnualGoalService.findGoalListByCondition(query);
        if(CollectionUtils.isNotEmpty(annualGoalList)){
            for(HospitalAnnualGoalVO aunualGoalVO:annualGoalList){
                result.put(aunualGoalVO.getEventId()+"y",aunualGoalVO);
            }
        }
        //查询月份目标
        query.setMonth(yearmonth);
        List<HospitalMonthGoalVO> monthGoalList=hospitalMonthGoalService.findGoalListByCondition(query);
        if(CollectionUtils.isNotEmpty(monthGoalList)){
            for(HospitalMonthGoalVO monthGoalVO:monthGoalList){
                result.put(monthGoalVO.getEventId()+"m",monthGoalVO);
            }
        }
        //查询去年同期
        query.setMonth(lastYearmonth);
        List<HospitalMonthGoalVO> lastYearMonthGoalList=hospitalMonthGoalService.findGoalListByCondition(query);
        if(CollectionUtils.isNotEmpty(lastYearMonthGoalList)){
            for(HospitalMonthGoalVO monthGoalVO:lastYearMonthGoalList){
                result.put(monthGoalVO.getEventId()+"ym-1",monthGoalVO);
            }
        }
        return CommonResponse.getSuccessResponse(result);
    }

}

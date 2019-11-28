package com.paladin.qos.controller.statistics;


import com.paladin.qos.model.data.DataUnit;
import com.paladin.qos.service.data.DataUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.controller.analysis.AnalysisRequest;
import com.paladin.qos.service.analysis.AnalysisService;
import com.paladin.qos.service.statistics.ExpensesService;

import java.util.ArrayList;
import java.util.List;

/**门急诊，住院费用统计   
 * @author MyKite
 * @version 2019年8月29日 下午3:57:16 
 */
@Controller
@RequestMapping("/qos")
public class ExpensesController {

    @Autowired
    private ExpensesService expensesService;
    
    @Autowired
    private AnalysisService analysisService;

    @Autowired
    private DataUnitService dataUnitService;

    @RequestMapping("/expenses/index")
    public String index(){
	return "/qos/statistics/expenses_data";
    }
    
    @PostMapping("/expenses/referral/count")
    @ResponseBody
    public Object findexpenses(AnalysisRequest request){
	return CommonResponse.getSuccessResponse(expensesService.inOutPersonCount(request));
    }
    
    @RequestMapping("/medication/index")
    public String indexMedication(){
	return "/qos/statistics/rational_medication_data";
    }

    //耗材费用页面
    @RequestMapping("/cost/produce")
    public String produce(Model model){
        List<Integer> types=new ArrayList<>();
        types.add(DataUnit.TYPE_HOSPITAL);
        model.addAttribute("unit", dataUnitService.selectData(types));
        return "/qos/hospitalData/produce_cost_data";
    }


    //手术费用页面
    @RequestMapping("/cost/operation")
    public String operation(){
        return "/qos/hospitalData/operation_cost_data";
    }


    //根据
}

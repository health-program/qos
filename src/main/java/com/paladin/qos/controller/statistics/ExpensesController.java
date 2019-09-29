package com.paladin.qos.controller.statistics;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.controller.analysis.AnalysisRequest;
import com.paladin.qos.service.analysis.AnalysisService;
import com.paladin.qos.service.statistics.ExpensesService;

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

}

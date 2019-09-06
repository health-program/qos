package com.paladin.qos.controller.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.service.statistics.ExpensesService;
import com.paladin.qos.service.statistics.dto.ExpensesQuery;

/**门急诊，住院费用统计   
 * @author MyKite
 * @version 2019年8月29日 下午3:57:16 
 */
@Controller
@RequestMapping("/qos")
public class ExpensesController {

    @Autowired
    private ExpensesService expensesService;
    
    @RequestMapping("/expenses/index")
    public String index(){
	return "/qos/statistics/expenses_data";
    }
    
    @RequestMapping("/expenses/find")
    @ResponseBody
    public Object findExpenses(ExpensesQuery query){
	return CommonResponse.getSuccessResponse(expensesService.findExpensesCount(query));
    }
    
    @GetMapping("/expenses/find/month")
    @ResponseBody
    public Object findMonth(ExpensesQuery query){
	return CommonResponse.getSuccessResponse(expensesService.findMonth(query));
    }
    
    @RequestMapping("/medication/index")
    public String indexMedication(){
	return "/qos/statistics/rational_medication_data";
    }
    
    @RequestMapping("/medication/find")
    @ResponseBody
    public Object findMedication(ExpensesQuery query){
	return CommonResponse.getSuccessResponse(expensesService.findRotionalCount(query));
    }
}

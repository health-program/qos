package com.paladin.qos.controller.statistics;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.model.data.DataUnit;
import com.paladin.qos.service.data.DataUnitService;


/** 医保支付方式每人均费用分析  
 * @author MyKite
 * @version 2019年11月16日 下午3:58:41 
 */
@Controller
@RequestMapping("/qos/pay/way")
public class PayWayAnalysisController {
    
    @Autowired
    private DataUnitService dataUnitService;

    @RequestMapping("/index")
    public String index(Model model) {
	return "/qos/statistics/pay_way_analysis_index";
    }
    
    @GetMapping(value = "/hospital")
    @ResponseBody
    public Object hospital() {
	 List<Integer> type = new ArrayList<>();
	 type.add(DataUnit.TYPE_HOSPITAL);
        return CommonResponse.getSuccessResponse(dataUnitService.selectData(type));
    }
}

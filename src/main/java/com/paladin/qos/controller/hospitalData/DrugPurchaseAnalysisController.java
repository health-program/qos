package com.paladin.qos.controller.hospitalData;

import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.service.hospital.DrugPurchaseAnalysisService;
import com.paladin.qos.service.hospital.dto.HospitalDataQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <药品采购分析>
 *
 * @author Huangguochen
 * @create 2019/11/16 16:14
 */
@Controller
@RequestMapping("/qos/drug/purchase/analysis")
public class DrugPurchaseAnalysisController extends ControllerSupport {

    @Autowired
    private DrugPurchaseAnalysisService  drugPurchaseAnalysisService;

    @GetMapping("/index")
    public String index() {
        return "/qos/exhibition/purchase_analysis_index";
    }

    @RequestMapping(value = "/find/money", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Object findPage(HospitalDataQuery query) {
        return CommonResponse.getSuccessResponse(drugPurchaseAnalysisService.findMoneyData(query));
    }

    @RequestMapping(value = "/find/quantity", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Object findQuantity(HospitalDataQuery query) {
        return CommonResponse.getSuccessResponse(drugPurchaseAnalysisService.findQuantityData(query));
    }

    @RequestMapping(value = "/find/moneys", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Object findMoneys(HospitalDataQuery query) {
        return CommonResponse.getSuccessResponse(drugPurchaseAnalysisService.findMoneysData(query));
    }

}

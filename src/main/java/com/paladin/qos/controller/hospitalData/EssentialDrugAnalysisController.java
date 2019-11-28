package com.paladin.qos.controller.hospitalData;

import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.service.analysis.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <基本药物分析>
 *
 * @author Huangguochen
 * @create 2019/11/18 8:46
 */
@Controller
@RequestMapping("/qos/drug/analysis")
public class EssentialDrugAnalysisController extends ControllerSupport {

    @Autowired
    private AnalysisService  analysisService;

    @GetMapping("/index")
    public String index() {
        return "/qos/exhibition/drug_analysis_index";
    }

    @RequestMapping(value = "/find/pm", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Object findPage() {
        return CommonResponse.getSuccessResponse("",analysisService.getTotalData("V80014"));
    }

}

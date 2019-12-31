package com.paladin.qos.controller.hospitalData;

import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.controller.analysis.AnalysisRequest;
import com.paladin.qos.service.hospital.HospitalDataScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <医院上传数据得分>
 *
 * @author Huangguochen
 * @create 2019/12/31 15:47
 */
@Controller
@RequestMapping("/qos/hospital/data/score")
public class HospitalDataScoreController extends ControllerSupport {

    @Autowired
    private HospitalDataScoreService hospitalDataScoreService;

    @RequestMapping("/find/data")
    @ResponseBody
    public Object find(AnalysisRequest analysisRequest) {
        return CommonResponse.getSuccessResponse(hospitalDataScoreService.findData(analysisRequest));
    }

}

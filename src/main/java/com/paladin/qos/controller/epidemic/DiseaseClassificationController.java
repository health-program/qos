package com.paladin.qos.controller.epidemic;

import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.service.epidemic.DiseaseClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * <疾病分类>
 *
 * @author Huangguochen
 * @create 2019/12/12 14:11
 */
@Controller
@RequestMapping("/qos/epidemic/disease")
public class DiseaseClassificationController extends ControllerSupport {

    @Autowired
    private DiseaseClassificationService diseaseClassificationService;

    @RequestMapping("/index")
    public String index() {
        return "/qos/epidemic/epidemic_disease_classification_index";
    }

    @RequestMapping("/find/page")
    @ResponseBody
    public Object find(@RequestParam(required = false) String  sickness, @RequestParam(required = false) List<Integer> codes, @RequestParam(required = false) Date startTime, @RequestParam(required = false) Date endTime) {
        return CommonResponse.getSuccessResponse(diseaseClassificationService.searchOutbreakHappening(sickness,codes,startTime,endTime));
    }

}

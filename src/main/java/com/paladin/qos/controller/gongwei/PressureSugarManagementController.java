package com.paladin.qos.controller.gongwei;


import java.util.ArrayList;
import java.util.List;

import com.paladin.qos.controller.analysis.AnalysisRequest;
import com.paladin.qos.model.data.DataUnit;
import com.paladin.qos.service.data.DataUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.service.gongwei.vo.PressureSugarManagementVO;

@Controller
@RequestMapping("/qos/gongwei/pressureSugar")
public class PressureSugarManagementController {

    @Autowired
    private DataUnitService dataUnitService;

    @GetMapping("/index")
    public Object dataIndex(Model model) {
        List<Integer> types=new ArrayList<>();
        types.add(DataUnit.TYPE_COMMUNITY);
        model.addAttribute("unit", dataUnitService.selectData(types));
        return "/qos/exhibition/pressureSugar_index";
    }

    @RequestMapping(value = "/search/all", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object searchAll(AnalysisRequest request) {
        List<PressureSugarManagementVO> pressureSugarManagementVOList = new ArrayList<>();



        return CommonResponse.getSuccessResponse(pressureSugarManagementVOList);
    }

}

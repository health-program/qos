package com.paladin.qos.controller.gongwei;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.controller.gongwei.dto.PressureSugarRequest;
import com.paladin.qos.service.gongwei.vo.PressureSugarManagementVO;

@Controller
@RequestMapping("/qos/gongwei/pressureSugar")
public class PressureSugarManagementController {


    @GetMapping("/index")
    public Object dataIndex(Model model) {
        return "/qos/exhibition/pressureSugar_index";
    }

    @RequestMapping(value = "/search/all", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object searchAll(PressureSugarRequest request) {

        //todo test
        List<PressureSugarManagementVO> pressureSugarManagementVOList = new ArrayList<>();
        PressureSugarManagementVO pressureSugarManagementVO1 = new PressureSugarManagementVO();
        pressureSugarManagementVO1.setManagedCenter("张浦镇");
        pressureSugarManagementVO1.setPressureNumber(8310l);
        pressureSugarManagementVO1.setPressureManageNumber(8054l);
        pressureSugarManagementVO1.setPressureRecentReach(8000l);
        pressureSugarManagementVO1.setSugarNumber(8568l);
        pressureSugarManagementVO1.setSugarManageNumber(7262l);
        pressureSugarManagementVO1.setSugarRecentReach(8000l);

        PressureSugarManagementVO pressureSugarManagementVO2 = new PressureSugarManagementVO();
        pressureSugarManagementVO2.setManagedCenter("淀山湖镇");
        pressureSugarManagementVO2.setPressureNumber(8353l);
        pressureSugarManagementVO2.setPressureManageNumber(7551l);
        pressureSugarManagementVO2.setPressureRecentReach(8000l);
        pressureSugarManagementVO2.setSugarRecentReach(8000l);
        pressureSugarManagementVO2.setSugarNumber(8555l);
        pressureSugarManagementVO2.setSugarManageNumber(7078l);


        PressureSugarManagementVO pressureSugarManagementVO3 = new PressureSugarManagementVO();
        pressureSugarManagementVO3.setManagedCenter("花桥镇");
        pressureSugarManagementVO3.setPressureNumber(8550l);
        pressureSugarManagementVO3.setPressureManageNumber(8015l);
        pressureSugarManagementVO3.setPressureRecentReach(8000l);
        pressureSugarManagementVO3.setSugarRecentReach(8000l);
        pressureSugarManagementVO3.setSugarNumber(8999l);
        pressureSugarManagementVO3.setSugarManageNumber(7446l);

        PressureSugarManagementVO pressureSugarManagementVO4 = new PressureSugarManagementVO();
        pressureSugarManagementVO4.setManagedCenter("锦溪镇");
        pressureSugarManagementVO4.setPressureNumber(8050l);
        pressureSugarManagementVO4.setPressureManageNumber(7322l);
        pressureSugarManagementVO4.setPressureRecentReach(8000l);
        pressureSugarManagementVO4.setSugarRecentReach(8000l);
        pressureSugarManagementVO4.setSugarNumber(8928l);
        pressureSugarManagementVO4.setSugarManageNumber(7307l);


        pressureSugarManagementVOList.add(pressureSugarManagementVO1);
        pressureSugarManagementVOList.add(pressureSugarManagementVO2);
        pressureSugarManagementVOList.add(pressureSugarManagementVO3);
        pressureSugarManagementVOList.add(pressureSugarManagementVO4);

        return CommonResponse.getSuccessResponse(pressureSugarManagementVOList);

        // return CommonResponse.getSuccessResponse(archivesManagementService.findArchives(managedCenter,startDate,endDate));
    }

}

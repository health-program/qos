package com.paladin.qos.controller.gongwei;


import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.controller.gongwei.dto.PhysicalRequest;
import com.paladin.qos.service.exhibition.ArchivesManagementService;
import com.paladin.qos.service.exhibition.vo.PhysicalManagementVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/qos/gongwei/physical")
public class PhysicalManagementController {

    @Autowired
    private ArchivesManagementService archivesManagementService;

    @GetMapping("/index")
    public Object dataIndex(Model model) {
        return "/qos/exhibition/physical_index";
    }


    @RequestMapping(value = "/search/all", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object searchAll(PhysicalRequest request) {
        String managedCenter = request.getManagedCenter();
        String startDateStr = request.getStartDate();
        String endDateStr = request.getEndDate();
        Date startDate = null;
        Date endDate = null;
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            if (!StringUtils.isEmptyOrWhitespace(startDateStr)) {
                startDate = sf.parse(startDateStr);
            }
            if (!StringUtils.isEmptyOrWhitespace(endDateStr)){
                endDate = sf.parse(endDateStr);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //todo test
        List<PhysicalManagementVO> physicalManagementVOList = new ArrayList<>();
        PhysicalManagementVO physicalManagementVO1 = new PhysicalManagementVO();
        physicalManagementVO1.setManagedCenter("张浦镇");
        physicalManagementVO1.setOldPeopleNumber(8435l);
        physicalManagementVO1.setPhysicalNumber(8032l);
        physicalManagementVO1.setCompletePhysicalNumber(7937l);

        PhysicalManagementVO physicalManagementVO2 = new PhysicalManagementVO();
        physicalManagementVO2.setManagedCenter("淀山湖镇");
        physicalManagementVO2.setOldPeopleNumber(8861l);
        physicalManagementVO2.setPhysicalNumber(7721l);
        physicalManagementVO2.setCompletePhysicalNumber(7785l);

        PhysicalManagementVO physicalManagementVO3 = new PhysicalManagementVO();
        physicalManagementVO3.setManagedCenter("花桥镇");
        physicalManagementVO3.setOldPeopleNumber(8024l);
        physicalManagementVO3.setPhysicalNumber(6603l);
        physicalManagementVO3.setCompletePhysicalNumber(7239l);

        PhysicalManagementVO physicalManagementVO4 = new PhysicalManagementVO();
        physicalManagementVO4.setManagedCenter("锦溪镇");
        physicalManagementVO4.setOldPeopleNumber(8576l);
        physicalManagementVO4.setPhysicalNumber(8412l);
        physicalManagementVO4.setCompletePhysicalNumber(6953l);
        physicalManagementVOList.add(physicalManagementVO1);
        physicalManagementVOList.add(physicalManagementVO2);
        physicalManagementVOList.add(physicalManagementVO3);
        physicalManagementVOList.add(physicalManagementVO4);

        return CommonResponse.getSuccessResponse(physicalManagementVOList);

        // return CommonResponse.getSuccessResponse(archivesManagementService.findArchives(managedCenter,startDate,endDate));
    }

}

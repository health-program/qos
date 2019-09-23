package com.paladin.qos.controller.gongwei;


import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.analysis.impl.gongwei.archives.CreateArchivesRate;
import com.paladin.qos.analysis.impl.gongwei.archives.PublicArchivesRate;
import com.paladin.qos.analysis.impl.gongwei.physical.OldPeopleHealthManageRate;
import com.paladin.qos.analysis.impl.gongwei.physical.OldPeoplePhysicalRate;
import com.paladin.qos.controller.analysis.AnalysisRequest;
import com.paladin.qos.controller.gongwei.dto.PhysicalRequest;
import com.paladin.qos.service.analysis.AnalysisService;
import com.paladin.qos.service.analysis.data.DataCountUnit;
import com.paladin.qos.service.gongwei.ArchivesManagementService;
import com.paladin.qos.service.gongwei.vo.ArchivesManagementVO;
import com.paladin.qos.service.gongwei.vo.PhysicalManagementVO;
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
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/qos/gongwei/physical")
public class PhysicalManagementController {

    @Autowired
    private ArchivesManagementService archivesManagementService;

    @Autowired
    private AnalysisService analysisService;

    @GetMapping("/index")
    public Object dataIndex(Model model) {
        return "/qos/exhibition/physical_index";
    }


    @RequestMapping(value = "/search/all", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object searchAll(AnalysisRequest request) {
        //String unitId = request.getUnitId();
        Date startDate = request.getStartTime();
        Date endDate = request.getEndTime();

        List<PhysicalManagementVO> physicalManagementVOArrayList=new ArrayList<>();

        List<DataCountUnit> oldPeople = analysisService.countTotalNumByUnit(OldPeoplePhysicalRate.EVENT_ID,2, request.getStartTime(), request.getEndTime());
        List<DataCountUnit> physicalPeople = analysisService.countEventNumByUnit(OldPeoplePhysicalRate.EVENT_ID,2, request.getStartTime(), request.getEndTime());
        List<DataCountUnit> healthPeople = analysisService.countEventNumByUnit(OldPeopleHealthManageRate.EVENT_ID,2, request.getStartTime(), request.getEndTime());


        Map<String, Long> physicalPeopleMap = physicalPeople.stream().collect(
                Collectors.toMap(w -> w.getUnitId(),
                        w -> w.getCount()));

        Map<String, Long> healthPeopleMap = healthPeople.stream().collect(
                Collectors.toMap(w -> w.getUnitId(),
                        w -> w.getCount()));


        for (DataCountUnit oldPeopleData:oldPeople){
            PhysicalManagementVO physicalManagementVO=new PhysicalManagementVO();
            String unitId=oldPeopleData.getUnitId();
            if (!StringUtils.isEmpty(unitId)){
                physicalManagementVO.setUnitId(unitId);
                physicalManagementVO.setOldPeopleNumber(oldPeopleData.getCount());
                physicalManagementVO.setPhysicalNumber(physicalPeopleMap.get(unitId));
                physicalManagementVO.setCompletePhysicalNumber(healthPeopleMap.get(unitId));
                physicalManagementVOArrayList.add(physicalManagementVO);
            }
        }

        return CommonResponse.getSuccessResponse(physicalManagementVOArrayList);


    }

}

package com.paladin.qos.controller.gongwei;


import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.analysis.DataConstantContainer;
import com.paladin.qos.analysis.impl.fuyou.ycf.NewbornVisit;
import com.paladin.qos.analysis.impl.gongwei.archives.CreateArchivesRate;
import com.paladin.qos.analysis.impl.gongwei.archives.PublicArchivesRate;
import com.paladin.qos.controller.analysis.AnalysisRequest;
import com.paladin.qos.controller.gongwei.dto.ArchivesRequest;
import com.paladin.qos.model.familydoctor.FamilyDoctorUnit;
import com.paladin.qos.service.analysis.AnalysisService;
import com.paladin.qos.service.analysis.data.DataCountUnit;
import com.paladin.qos.service.familydoctor.FamilyDoctorUnitService;
import com.paladin.qos.service.gongwei.ArchivesManagementService;
import com.paladin.qos.service.gongwei.vo.ArchivesManagementVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;
import com.paladin.qos.analysis.DataConstantContainer.Unit;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/qos/gongwei/archives")
public class ArchivesController {

    @Autowired
    private AnalysisService analysisService;

    @Autowired
    private ArchivesManagementService archivesManagementService;

    @Autowired
    private FamilyDoctorUnitService familyDoctorUnitService;

    @GetMapping("/index")
    public Object dataIndex(Model model) {
        return "/qos/exhibition/archives_index";
    }


    @RequestMapping(value = "/search/all", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object searchAll( AnalysisRequest request) {
        String managedCenter = request.getUnitId();
        Date startDate = request.getStartTime();
        Date endDate = request.getEndTime();

        List<ArchivesManagementVO> archivesManagementVOList=new ArrayList<>();

        List<DataCountUnit> totalArchives = analysisService.countTotalNumByUnit(CreateArchivesRate.EVENT_ID,2, request.getStartTime(), request.getEndTime());
        List<DataCountUnit> eventArchives = analysisService.countEventNumByUnit(PublicArchivesRate.EVENT_ID,2, request.getStartTime(), request.getEndTime());
        List<FamilyDoctorUnit> familyDoctorUnits=familyDoctorUnitService.findAll();

        Map<String, Long> eventArchivesMap = eventArchives.stream().collect(
                Collectors.toMap(w -> w.getUnitId(),
                        w -> w.getCount()));

        Map<String, Long> familyDoctorUnitsMap = familyDoctorUnits.stream().collect(
                Collectors.toMap(w -> w.getId(),
                        w -> (long) (Double.parseDouble(w.getPopulation())*10000)));

        for (DataCountUnit totalArchivesData:totalArchives){
            ArchivesManagementVO archivesManagementVO=new ArchivesManagementVO();
            String unitId=totalArchivesData.getUnitId();
            if (!StringUtils.isEmpty(unitId)){
                archivesManagementVO.setUnitId(unitId);
                archivesManagementVO.setActiveArchivesNumber(totalArchivesData.getCount());
                archivesManagementVO.setPublicArchivesNumber(eventArchivesMap.get(unitId));
                archivesManagementVO.setPeopleNumber(familyDoctorUnitsMap.get(unitId));
                archivesManagementVOList.add(archivesManagementVO);
            }
        }


        return  CommonResponse.getSuccessResponse(archivesManagementVOList);
    }


}

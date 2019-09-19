package com.paladin.qos.controller.gongwei;


import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.analysis.DataConstantContainer;
import com.paladin.qos.controller.gongwei.dto.ArchivesRequest;
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

@Controller
@RequestMapping("/qos/gongwei/archives")
public class ArchivesManagementController {

    @Autowired
    private ArchivesManagementService archivesManagementService;

    @GetMapping("/index")
    public Object dataIndex(Model model) {
        return "/qos/exhibition/archives_index";
    }


    @RequestMapping(value = "/search/all", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object searchAll(ArchivesRequest request) {
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
        List<ArchivesManagementVO> archivesManagementVOList=new ArrayList<>();
        List<Unit> units = DataConstantContainer.getUnitListByType(3);
        ArchivesManagementVO archivesManagementVO=null;
        if (StringUtils.isEmptyOrWhitespace(managedCenter)){
            for (Unit unit : units) {
                String unitId = unit.getId();
                archivesManagementVO=archivesManagementService.findArchives(unitId,startDate,endDate);
                if (null!=archivesManagementVO){
                    if (null==archivesManagementVO.getActiveArchivesNumber()){
                        archivesManagementVO.setActiveArchivesNumber(0l);
                    }
                    if (null==archivesManagementVO.getPublicArchivesNumber()){
                        archivesManagementVO.setPublicArchivesNumber(0l);
                    }

                    archivesManagementVOList.add(archivesManagementVO);
                }

            }
            return  CommonResponse.getSuccessResponse(archivesManagementVOList);
        }else{
            return  CommonResponse.getSuccessResponse(archivesManagementService.findArchives(managedCenter,startDate,endDate));
        }

//        //todo test
//        List<ArchivesManagementVO> archivesManagementVOList = new ArrayList<>();
//        ArchivesManagementVO archivesManagementVO1 = new ArchivesManagementVO();
//        archivesManagementVO1.setManagedCenter("张浦镇");
//        archivesManagementVO1.setPeopleNumber(100367l);
//        archivesManagementVO1.setActiveArchivesNumber(87299l);
//        archivesManagementVO1.setPublicArchivesNumber(66950l);
//
//        ArchivesManagementVO archivesManagementVO2 = new ArchivesManagementVO();
//        archivesManagementVO2.setManagedCenter("淀山湖镇");
//        archivesManagementVO2.setPeopleNumber(36346l);
//        archivesManagementVO2.setActiveArchivesNumber(31054l);
//        archivesManagementVO2.setPublicArchivesNumber(23567l);
//
//        ArchivesManagementVO archivesManagementVO3 = new ArchivesManagementVO();
//        archivesManagementVO3.setManagedCenter("花桥镇");
//        archivesManagementVO3.setPeopleNumber(68104l);
//        archivesManagementVO3.setActiveArchivesNumber(56663l);
//        archivesManagementVO3.setPublicArchivesNumber(39868l);
//
//        ArchivesManagementVO archivesManagementVO4 = new ArchivesManagementVO();
//        archivesManagementVO4.setManagedCenter("锦溪镇");
//        archivesManagementVO4.setPeopleNumber(58246l);
//        archivesManagementVO4.setActiveArchivesNumber(48886l);
//        archivesManagementVO4.setPublicArchivesNumber(37251l);
//        archivesManagementVOList.add(archivesManagementVO1);
//        archivesManagementVOList.add(archivesManagementVO2);
//        archivesManagementVOList.add(archivesManagementVO3);
//        archivesManagementVOList.add(archivesManagementVO4);

//        return CommonResponse.getSuccessResponse(archivesManagementVOList);

        // return CommonResponse.getSuccessResponse(archivesManagementService.findArchives(managedCenter,startDate,endDate));
    }

}

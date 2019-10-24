package com.paladin.qos.controller;

import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.analysis.DataConstantContainer;
import com.paladin.qos.analysis.DataConstantContainer.Event;
import com.paladin.qos.analysis.TimeUtil;
import com.paladin.qos.controller.analysis.AnalysisRequest;
import com.paladin.qos.model.data.DataEvent;
import com.paladin.qos.model.familydoctor.FamilyDoctorUnit;
import com.paladin.qos.model.home.Sign;
import com.paladin.qos.model.register.Register;
import com.paladin.qos.service.analysis.AnalysisService;
import com.paladin.qos.service.analysis.data.DataCountUnit;
import com.paladin.qos.service.analysis.data.DataSigningMonth;
import com.paladin.qos.service.familydoctor.FamilyDoctorPersonnelService;
import com.paladin.qos.service.familydoctor.FamilyDoctorUnitService;
import com.paladin.qos.service.gongwei.ArchivesManagementService;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author MyKite
 * @version 2019年6月24日 下午3:04:58
 */
@Controller
@RequestMapping("/home/page/qos/display")
public class DataDisplayController {

    @Autowired
    private FamilyDoctorUnitService familyDoctorUnitService;

    @Autowired
    private AnalysisService analysisService;

    //当前时间前12个月建档率
    @RequestMapping(value = "/data/get/month/archives/rate", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object getArchivesRate() {
        Long number=0l;
        List<FamilyDoctorUnit> familyDoctorUnits = familyDoctorUnitService.findAll();
        for (FamilyDoctorUnit familyDoctorUnit : familyDoctorUnits) {
            BigDecimal value1 = new BigDecimal(familyDoctorUnit.getPopulation());
            BigDecimal value2 = new BigDecimal("10000");
            number += (long) value1.multiply(value2).doubleValue();
        }
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -12);
        c.set(Calendar.DAY_OF_MONTH, 1);
        Date startDate = c.getTime();

        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.MONTH, -1);
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date endDate = ca.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        ArrayList<Date> result = new ArrayList<>();
        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        min.setTime(startDate);
        c.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

        max.setTime(endDate);
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

        Calendar curr = min;
        while (curr.before(max)) {
            result.add(curr.getTime());
            curr.add(Calendar.MONTH, 1);
        }

        Map<String, Object> map = new HashMap<>();
        Date currentDate=new Date();
        String strCurrentDate=sdf.format(currentDate);
        List<DataSigningMonth> dataList=analysisService.getArchivesRate();
        if (!CollectionUtils.isEmpty(dataList)){
            for (DataSigningMonth data:dataList){
                map.put(data.getMonth(),data.getCount());
            }
        }
        return CommonResponse.getSuccessResponse(analysisService.getArchivesRate());
    }




}


 
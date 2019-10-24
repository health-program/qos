package com.paladin.qos.controller;

import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.model.familydoctor.FamilyDoctorUnit;
import com.paladin.qos.service.analysis.AnalysisService;
import com.paladin.qos.service.analysis.data.DataSigningMonth;
import com.paladin.qos.service.familydoctor.FamilyDoctorUnitService;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author MyKite
 * @version 2019年6月24日 下午3:04:58
 */
@Controller
@RequestMapping("/data/display")
public class DataDisplayController {

    @Autowired
    private FamilyDoctorUnitService familyDoctorUnitService;

    @Autowired
    private AnalysisService analysisService;

    //当前时间前12个月建档率
    @RequestMapping(value = "/get/month/archives/rate", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object getArchivesRate() {
        Long number = 0l;
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
        ArrayList<String> result = new ArrayList<>();
        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        min.setTime(startDate);
        c.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

        max.setTime(endDate);
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

        Calendar curr = min;
        while (curr.before(max)) {
            result.add(sdf.format(curr.getTime()));
            curr.add(Calendar.MONTH, 1);
        }

        Map<String, Double> map = new HashMap<>();
        Date currentDate = new Date();
        String strCurrentDate = sdf.format(currentDate);
        List<DataSigningMonth> dataList = analysisService.getArchivesRate();
        long total = 0l;
        if (!CollectionUtils.isEmpty(dataList)) {
            Iterator<DataSigningMonth> iterator = dataList.iterator();
            while (iterator.hasNext()) {
                DataSigningMonth data = iterator.next();
                if (StringUtils.equals(data.getMonth(), strCurrentDate)) {
                    iterator.remove();
                } else {
                    for (String strMonth : result) {
                        if (StringUtils.equals(strMonth, data.getMonth())) {
                            String strDate=strMonth+"-01";
                            map.put(strMonth,(analysisService.getArchivesNumber(string2Date(strDate))+Long.valueOf(data.getCount()))/(double)number);
                        }
                    }
                }
            }
        }
        return CommonResponse.getSuccessResponse(map);
    }

    public static Date string2Date(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (!StringUtils.isEmpty(dateStr)) {
            try {
                return sdf.parse(dateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}


 
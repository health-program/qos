package com.paladin.qos.controller;

import com.alibaba.fastjson.JSON;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.controller.analysis.AnalysisRequest;
import com.paladin.qos.model.familydoctor.FamilyDoctorUnit;
import com.paladin.qos.model.gongwei.EntityGongweiFamily;
import com.paladin.qos.service.analysis.AnalysisService;
import com.paladin.qos.service.analysis.data.DataMonthRate;
import com.paladin.qos.service.analysis.data.DataSigningMonth;
import com.paladin.qos.service.familydoctor.FamilyDoctorUnitService;
import org.apache.shiro.crypto.hash.Hash;
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

//    //当前时间前12个月建档率
//    @RequestMapping(value = "/get/month/archives/rate", method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
//    public Object getArchivesRate(AnalysisRequest request) {
//        Long number = 0l;
//        List<FamilyDoctorUnit> familyDoctorUnits = familyDoctorUnitService.findAll();
//        for (FamilyDoctorUnit familyDoctorUnit : familyDoctorUnits) {
//            BigDecimal value1 = new BigDecimal(familyDoctorUnit.getPopulation());
//            BigDecimal value2 = new BigDecimal("10000");
//            number += (long) value1.multiply(value2).doubleValue();
//        }
//        Calendar c = Calendar.getInstance();
//        c.add(Calendar.MONTH, -12);
//        c.set(Calendar.DAY_OF_MONTH, 1);
//        Date startDate = c.getTime();
//
//        Calendar ca = Calendar.getInstance();
//        ca.add(Calendar.MONTH, -1);
//        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
//        Date endDate = ca.getTime();
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
//        ArrayList<String> result = new ArrayList<>();
//        Calendar min = Calendar.getInstance();
//        Calendar max = Calendar.getInstance();
//
//        min.setTime(startDate);
//        c.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);
//
//        max.setTime(endDate);
//        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);
//
//        Calendar curr = min;
//        while (curr.before(max)) {
//            result.add(sdf.format(curr.getTime()));
//            curr.add(Calendar.MONTH, 1);
//        }
//
//        Date currentDate = new Date();
//        String strCurrentDate = sdf.format(currentDate);
//        List<DataSigningMonth> dataList = analysisService.getArchivesRate();
//        List<DataMonthRate> dataMonthRates=new ArrayList<>();
//        long total = 0l;
//        if (!CollectionUtils.isEmpty(dataList)) {
//            Iterator<DataSigningMonth> iterator = dataList.iterator();
//            while (iterator.hasNext()) {
//                DataSigningMonth data = iterator.next();
//                if (StringUtils.equals(data.getMonth(), strCurrentDate)) {
//                    iterator.remove();
//                } else {
//                    for (String strMonth : result) {
//                        if (StringUtils.equals(strMonth, data.getMonth())) {
//                            DataMonthRate dataMonthRate=new DataMonthRate();
//                            dataMonthRate.setMonth(strMonth);
//                            String strDate=strMonth+"-01";
//                            Double value=(analysisService.getArchivesNumber(string2Date(strDate))+Long.valueOf(data.getCount()))/(double)number;
//                            dataMonthRate.setRate(value);
//                            dataMonthRates.add(dataMonthRate);
//                        }
//                    }
//                }
//            }
//        }
//        return CommonResponse.getSuccessResponse(dataMonthRates);
//    }

//    public static Date string2Date(String dateStr) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        if (!StringUtils.isEmpty(dateStr)) {
//            try {
//                return sdf.parse(dateStr);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }



    //当前时间前12个月建档率
    @RequestMapping(value = "/get/month/archives/rate", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object getArchivesRate(AnalysisRequest request) {
        Long number = 0l;
        Long sum=0l;
        List<FamilyDoctorUnit> familyDoctorUnits = familyDoctorUnitService.findAll();
        for (FamilyDoctorUnit familyDoctorUnit : familyDoctorUnits) {
            BigDecimal value1 = new BigDecimal(familyDoctorUnit.getPopulation());
            BigDecimal value2 = new BigDecimal("10000");
            number += (long) value1.multiply(value2).doubleValue();
        }

        String item = analysisService.getTotalData("V30011");
        List<EntityGongweiFamily> list = toBeanList(item,EntityGongweiFamily.class);
        String item1 = analysisService.getTotalData("V30012");

        List<EntityGongweiFamily> list1 = toBeanList(item1,EntityGongweiFamily.class);
        if (!CollectionUtils.isEmpty(list1)){
            sum=Long.valueOf(list1.get(0).getCount());
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
        Collections.sort(result);
        long total = 0l;
        List<DataMonthRate> dataMonthRates=new ArrayList<>();
        for(String month:result){
            for (EntityGongweiFamily data:list){
                if (StringUtils.equals(month,data.getMonth())){
                    DataMonthRate dataMonthRate=new DataMonthRate();
                    dataMonthRate.setMonth(month);
                    dataMonthRate.setRate((sum+Long.valueOf(data.getCount())+total)/(double)(number));
                    total+=Long.valueOf(data.getCount());
                    dataMonthRates.add(dataMonthRate);
                }
            }
        }
        return CommonResponse.getSuccessResponse(dataMonthRates);
    }


    public static <T> List<T> toBeanList(String json, Class<T> clazz) {
        List<T> beanList = null;

        try {
            if (json != null) {
                beanList = JSON.parseArray(json, clazz);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return beanList;
    }
}


 
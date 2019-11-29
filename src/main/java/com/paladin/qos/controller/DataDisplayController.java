package com.paladin.qos.controller;

import com.alibaba.fastjson.JSON;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.analysis.DataConstantContainer;
import com.paladin.qos.controller.analysis.AnalysisRequest;
import com.paladin.qos.controller.analysis.HospitalRequest;
import com.paladin.qos.model.data.DataEvent;
import com.paladin.qos.model.familydoctor.FamilyDoctorUnit;
import com.paladin.qos.model.gongwei.EntityGongwei;
import com.paladin.qos.model.gongwei.EntityGongweiFamily;
import com.paladin.qos.service.analysis.AnalysisService;
import com.paladin.qos.service.analysis.data.AnalysisUnit;
import com.paladin.qos.service.analysis.data.DataCountUnit;
import com.paladin.qos.service.analysis.data.DataMonthRate;
import com.paladin.qos.service.analysis.data.DataSigningMonth;
import com.paladin.qos.service.familydoctor.FamilyDoctorUnitService;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
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

    //常住居民数，健康档案数，档案公开数
    //老年人体检，健康管理
    @RequestMapping(value = "/search/all", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object getTotalData(AnalysisRequest request) {
        Map<String, Object> map = new HashMap<>();
        List<DataConstantContainer.Unit> units = DataConstantContainer.getCommunityList();
        List<String> eventIds=request.getEventIds();
        for (String eventId:eventIds){
            String item = analysisService.getTotalData(eventId);
            if(!StringUtils.isEmpty(item)){
                List<EntityGongwei> danganList = toBeanList(item,EntityGongwei.class);
                List<EntityGongwei> list=new ArrayList<>();
                for(EntityGongwei shequ:danganList){
                    for(DataConstantContainer.Unit unit : units){
                        if(shequ.MANAGEDCENTERCODE.equals(unit.getSource().getGongweiCode())){
                            shequ.setUnitId(unit.getId());
                            shequ.setUnitName(unit.getName());
                            list.add(shequ);
                        }
                    }
                }
                orderByUnit(list);
                map.put(eventId,list);
            }
        }
        return  CommonResponse.getSuccessResponse(map);
    }


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

    private void orderByUnit(List<EntityGongwei> list) {
        if (list != null && list.size() > 0) {
            Collections.sort(list, new Comparator<EntityGongwei>() {
                @Override
                public int compare(EntityGongwei o1, EntityGongwei o2) {
                    String uid1 = o1.getUnitId();
                    String uid2 = o1.getUnitId();
                    return DataConstantContainer.getUnit(uid1).getOrderNum() > DataConstantContainer.getUnit(uid2).getOrderNum() ? 1 : -1;
                }
            });
        }
    }



    @RequestMapping(value = "/search/hospital/data", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object getHospitalData(HospitalRequest request) {
        String year=request.getYear();
        Date startYear=null;
        Date endYear=null;
        Date startMonth=null;
        Date endMonth=null;

        Date startYear1=null;
        Date endYear1=null;

        Date startYear2=null;
        Date endYear2=null;

        Date startMonthOfLastYear=null;
        Date endMonthOfLastYear=null;

        if (!StringUtils.isEmpty(year)){
            if (year.contains("-")){
                String[] date = year.split("-");
                startYear=getYearFirst(Integer.valueOf(date[0]));
                endYear=getYearFirst(Integer.valueOf(date[0])+1);

                startYear1=getYearFirst(Integer.valueOf(date[0])-1);
                endYear1=getYearFirst(Integer.valueOf(date[0]));

                startYear2=getYearFirst(Integer.valueOf(date[0])-2);
                endYear2=getYearFirst(Integer.valueOf(date[0])-1);

                startMonth=getMonthFirst(Integer.valueOf(date[0]),Integer.valueOf(date[1]));
                endMonth=getMonthFirst(Integer.valueOf(date[0]),Integer.valueOf(date[1])+1);

                startMonthOfLastYear=getMonthFirst(Integer.valueOf(date[0])-1,Integer.valueOf(date[1]));
                endMonthOfLastYear=getMonthFirst(Integer.valueOf(date[0])-1,Integer.valueOf(date[1])+1);

            }else{
                startYear=getYearFirst(Integer.valueOf(year));
                endYear=getYearFirst(Integer.valueOf(year)+1);

                startYear1=getYearFirst(Integer.valueOf(year)-1);
                endYear1=getYearFirst(Integer.valueOf(year));

                startYear2=getYearFirst(Integer.valueOf(year)-2);
                endYear2=getYearFirst(Integer.valueOf(year)-1);
            }

        }
        List<String> eventIds = request.getEventIds();
        if (eventIds != null && eventIds.size() > 0) {
            Map<String, Object> map = new HashMap<>();
            for (String eventId : eventIds) {
                DataConstantContainer.Event event = DataConstantContainer.getEvent(eventId);
                if (event != null) {
                    int eventType = event.getEventType();
                    int unitType = getUnitType(event);
                    if (DataEvent.EVENT_TYPE_COUNT == eventType) {
                        List<DataCountUnit> countUnits=analysisService.countTotalNumByUnit(eventId, unitType, startYear, endYear, null);
                        List<DataCountUnit> countUnits1=analysisService.countTotalNumByUnit(eventId, unitType, startYear1, endYear1, null);
                        List<DataCountUnit> countUnits2=analysisService.countTotalNumByUnit(eventId, unitType, startYear2, endYear2, null);
                        if (!CollectionUtils.isEmpty(countUnits)) {
                            getCountData(request,countUnits);
                            getCountData(request,countUnits1);
                            getCountData(request,countUnits2);
                            map.put(eventId+"y", countUnits);
                            map.put(eventId+"y-1", countUnits1);
                            map.put(eventId+"y-2", countUnits2);
                            if (null!=startMonth){
                                List<DataCountUnit> countUnitsMonth=analysisService.countTotalNumByUnit(eventId, unitType, startMonth, endMonth, null);
                                if (!CollectionUtils.isEmpty(countUnitsMonth)) {
                                    getCountData(request, countUnitsMonth);
                                    map.put(eventId + "m", countUnitsMonth);
                                }
                            }
                            if(null!=startMonthOfLastYear){
                                List<DataCountUnit> countUnitsMonthOfLastYear=analysisService.countTotalNumByUnit(eventId, unitType, startMonthOfLastYear, endMonthOfLastYear, null);
                                if (!CollectionUtils.isEmpty(countUnitsMonthOfLastYear)) {
                                    getCountData(request, countUnitsMonthOfLastYear);
                                    map.put(eventId + "ym-1", countUnitsMonthOfLastYear);
                                }
                            }
                        }
                    } else if(DataEvent.EVENT_TYPE_RATE == eventType) {
                        List<AnalysisUnit> analysisUnits=analysisService.getAnalysisResultByUnit(eventId, unitType, startYear, endYear, null);
                        List<AnalysisUnit> analysisUnits1=analysisService.getAnalysisResultByUnit(eventId, unitType, startYear1, endYear1, null);
                        List<AnalysisUnit> analysisUnits2=analysisService.getAnalysisResultByUnit(eventId, unitType, startYear2, endYear2, null);
                        if (!CollectionUtils.isEmpty(analysisUnits)) {
                            getRateData(request,analysisUnits);
                            getRateData(request,analysisUnits1);
                            getRateData(request,analysisUnits2);
                            map.put(eventId+"y", analysisUnits);
                            map.put(eventId+"y-1", analysisUnits1);
                            map.put(eventId+"y-2", analysisUnits2);
                            if (null!=startMonth){
                                List<AnalysisUnit> analysisUnitsMonth=analysisService.getAnalysisResultByUnit(eventId, unitType, startMonth, endMonth, null);
                                if (!CollectionUtils.isEmpty(analysisUnitsMonth)) {
                                    getRateData(request, analysisUnitsMonth);
                                    map.put(eventId + "m", analysisUnitsMonth);
                                }
                            }
                        }
                    }
                }
            }
            return CommonResponse.getSuccessResponse(map);
        }
        return  CommonResponse.getErrorResponse();
    }


    private void getRateData(HospitalRequest request,List<AnalysisUnit> analysisUnits ){
        if (!CollectionUtils.isEmpty(analysisUnits)){
            Iterator<AnalysisUnit> it=analysisUnits.iterator();
            while(it.hasNext()){
                AnalysisUnit data=it.next();
                if (CollectionUtils.isEmpty(request.getUnitIds())){
                    if(!StringUtils.isEmpty(request.getUnitId() ) && !data.getUnitId().equals(request.getUnitId())){
                        it.remove();
                    }
                }else{
                    for(String unitId:request.getUnitIds()){
                        if(!data.getUnitId().equals(unitId)){
                            it.remove();
                        }
                    }
                }

            }
        }

    }

    private void getCountData(HospitalRequest request,List<DataCountUnit> dataCountUnits ){
        if (!CollectionUtils.isEmpty(dataCountUnits)){
            Iterator<DataCountUnit> it=dataCountUnits.iterator();
            while(it.hasNext()){
                DataCountUnit data=it.next();
                if (CollectionUtils.isEmpty(request.getUnitIds())){
                    if(!StringUtils.isEmpty(request.getUnitId() ) && !data.getUnitId().equals(request.getUnitId())){
                        it.remove();
                    }
                }else{
                    for(String unitId:request.getUnitIds()){
                        if(!data.getUnitId().equals(unitId)){
                            it.remove();
                        }
                    }
                }

            }
        }

    }

    private int getUnitType(DataConstantContainer.Event event) {
        int targetType = event.getTargetType();
        if (targetType == DataEvent.TARGET_TYPE_COMMUNITY)
            return 2;
        if (targetType == DataEvent.TARGET_TYPE_HOSPITAL)
            return 1;
        return 0;
    }

    //取一年的第一天
    private  Date getYearFirst(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    private  Date getMonthFirst(int year,int month){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month-1);
        Date currMonthFirst = calendar.getTime();
        return currMonthFirst;
    }

}


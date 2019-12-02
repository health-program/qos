package com.paladin.qos.controller;


import com.alibaba.fastjson.JSON;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.analysis.DataConstantContainer;
import com.paladin.qos.controller.analysis.AnalysisRequest;
import com.paladin.qos.model.familydoctor.EntitySign;
import com.paladin.qos.model.gongwei.EntityGongwei;
import com.paladin.qos.service.analysis.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import java.util.*;

@Controller
@RequestMapping("/qos/sign")
public class SignController {

    @Autowired
    private AnalysisService analysisService;
    //签约管理
    @RequestMapping(value = "/get/manage/data", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object getManageData(AnalysisRequest request,String year) {
        Map<String, Object> map = new HashMap<>();
        List<DataConstantContainer.Unit> units = DataConstantContainer.getCommunityList();
        List<String> eventIds=request.getEventIds();
        for (String eventId:eventIds){
            String item = analysisService.getTotalData(eventId);
            if(!StringUtils.isEmpty(item)){
                List<EntitySign> danganList = toBeanList(item,EntitySign.class);
                List<EntitySign> newSignList=new ArrayList<>();
                if (!StringUtils.isEmpty(year)){
                    for (EntitySign entitySign:danganList){
                        if (!StringUtils.isEmpty(entitySign.getYear())&&entitySign.getYear().equals(year)){
                            newSignList.add(entitySign);
                        }
                    }
                }else{
                    newSignList.addAll(danganList);
                }
                List<EntitySign> list=new ArrayList<>();
                for(EntitySign shequ:newSignList){
                    for(DataConstantContainer.Unit unit : units){
                        if(!StringUtils.isEmpty(shequ.getUnit())&&shequ.getUnit().equals(unit.getSource().getGongweiCode())){
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

    
    
    @RequestMapping(value = "/get/group/data", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object getGroupData(AnalysisRequest request,String year) {
        Map<String, Object> map = new HashMap<>();
        List<DataConstantContainer.Unit> units = DataConstantContainer.getCommunityList();
        List<String> eventIds=request.getEventIds();
        for (String eventId:eventIds){
            String item = analysisService.getTotalData(eventId);
            if(!StringUtils.isEmpty(item)){
                List<EntitySign> danganList = toBeanList(item,EntitySign.class);
                List<EntitySign> newSignList=new ArrayList<>();
                if (!StringUtils.isEmpty(year)){
                    for (EntitySign entitySign:danganList){
                        if (!StringUtils.isEmpty(entitySign.getYear())&&entitySign.getYear().equals(year)){
                            newSignList.add(entitySign);
                        }
                    }
                }else{
                    newSignList.addAll(danganList);
                }
                List<EntitySign> list=new ArrayList<>();
                for(EntitySign shequ:newSignList){
                    for(DataConstantContainer.Unit unit : units){
                        if(!StringUtils.isEmpty(shequ.getUnit())&&shequ.getUnit().equals(unit.getSource().getGongweiCode())){
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

    private void orderByUnit(List<EntitySign> list) {
        if (list != null && list.size() > 0) {
            Collections.sort(list, new Comparator<EntitySign>() {
                @Override
                public int compare(EntitySign o1, EntitySign o2) {
                    String uid1 = o1.getUnitId();
                    String uid2 = o2.getUnitId();
                    return DataConstantContainer.getUnit(uid1).getOrderNum() > DataConstantContainer.getUnit(uid2).getOrderNum() ? 1 : -1;
                }
            });
        }
    }
}

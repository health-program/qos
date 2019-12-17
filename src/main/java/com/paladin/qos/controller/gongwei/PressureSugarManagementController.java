package com.paladin.qos.controller.gongwei;


import com.alibaba.fastjson.JSON;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.analysis.DataConstantContainer;
import com.paladin.qos.analysis.DataConstantContainer.*;
import com.paladin.qos.controller.analysis.AnalysisRequest;
import com.paladin.qos.model.data.DataUnit;
import com.paladin.qos.model.gongwei.EntityGongwei;
import com.paladin.qos.model.gongwei.EntityGongweiYearReport;
import com.paladin.qos.service.analysis.AnalysisConstant;
import com.paladin.qos.service.analysis.AnalysisService;
import com.paladin.qos.service.analysis.data.DataCountUnit;
import com.paladin.qos.service.data.DataUnitService;
import com.paladin.qos.service.data.vo.DataUnitVO;
import com.paladin.qos.service.gongwei.PressureSugarManagementService;
import com.paladin.qos.service.gongwei.vo.PressureSugarManagementVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/qos/gongwei/pressureSugar")
public class PressureSugarManagementController {

    @Autowired
    private DataUnitService dataUnitService;

    @Autowired
    private AnalysisService analysisService;

    @Autowired
    private PressureSugarManagementService pressureSugarManagementService;

    @GetMapping("/index")
    public Object dataIndex(Model model) {
//        List<Integer> types=new ArrayList<>();
//        types.add(DataUnit.TYPE_COMMUNITY);
//
//        List<DataUnitVO> dataUnitVOList =dataUnitService.selectData(types);
//        Iterator<DataUnitVO> it=dataUnitVOList.iterator();
//        while(it.hasNext()){
//            DataUnitVO dataUnitVO=it.next();
//            if(StringUtils.equalsIgnoreCase("320583810343",dataUnitVO.getId())){
//                it.remove();
//            }
//        }
//        model.addAttribute("unit", dataUnitVOList);
        return "/qos/exhibition/pressureSugar_index";
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

    // 单位排序
    private void orderByUnit(List<EntityGongweiYearReport> list) {
        if (list != null && list.size() > 0) {
            Collections.sort(list, new Comparator<EntityGongweiYearReport>() {
                @Override
                public int compare(EntityGongweiYearReport o1, EntityGongweiYearReport o2) {
                    String uid1 = o1.getUnitId();
                    String uid2 = o2.getUnitId();
                    return DataConstantContainer.getUnit(uid1).getOrderNum() > DataConstantContainer.getUnit(uid2).getOrderNum() ? 1 : -1;
                }
            });
        }
    }

//    @RequestMapping(value = "/search/all", method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
//    public Object searchAll(AnalysisRequest request,String year) {
//    	Map<String, Object> map = new HashMap<>();
//    	List<Unit> units = DataConstantContainer.getCommunityList();
//    	List<EntityGongwei> pressureList1=new ArrayList<EntityGongwei>();
//    	List<EntityGongwei> sugarList1=new ArrayList<EntityGongwei>();
//
//    	List<EntityGongwei> managePressuerList1=new ArrayList<EntityGongwei>();
//    	List<EntityGongwei> manageSugarList1=new ArrayList<EntityGongwei>();
//
//
//    	List<EntityGongwei> pressureList = analysisService.getPressureTotalDataFromLocal(year);//高血压管理数，高血压随访数
//    	List<EntityGongwei> sugarList = analysisService.getSugarTotalDataFromLocal(year);//糖尿病管理数，糖尿病随访数
//
//    	String item4 = analysisService.getTotalData("V30004");//管理人群血压
//    	String item5 = analysisService.getTotalData("V30005");//管理人群糖尿病
//
//    	if(!StringUtils.isEmpty(item4)){
//        	List<EntityGongwei> managePressuerList = toBeanList(item4,EntityGongwei.class);
//        	for(EntityGongwei shequ:managePressuerList){
//
//        		for(Unit unit : units){
//        			if(shequ.MANAGEDCENTERCODE.equals(unit.getSource().getGongweiCode())){
//        				shequ.setUnitId(unit.getId());
//        				shequ.setUnitName(unit.getName());
//        				managePressuerList1.add(shequ);
//        			}
//        		}
//        	}
//    	}
//    	if(!StringUtils.isEmpty(item5)){
//        	List<EntityGongwei> manageSugarList = toBeanList(item5,EntityGongwei.class);
//        	for(EntityGongwei shequ:manageSugarList){
//
//        		for(Unit unit : units){
//        			if(shequ.MANAGEDCENTERCODE.equals(unit.getSource().getGongweiCode())){
//        				shequ.setUnitId(unit.getId());
//        				shequ.setUnitName(unit.getName());
//        				manageSugarList1.add(shequ);
//        			}
//        		}
//        	}
//    	}
//        if(pressureList!=null){
//        	for(EntityGongwei shequ:pressureList){
//
//        		for(Unit unit : units){
//        			if(shequ.MANAGEDCENTERCODE.equals(unit.getSource().getGongweiCode())){
//        				shequ.setUnitId(unit.getId());
//        				shequ.setUnitName(unit.getName());
//        				pressureList1.add(shequ);
//        			}
//        		}
//        	}
//    	}
//
//        if(sugarList!=null){
//        	for(EntityGongwei shequ:sugarList){
//        		for(Unit unit : units){
//        			if(shequ.MANAGEDCENTERCODE.equals(unit.getSource().getGongweiCode())){
//        				shequ.setUnitId(unit.getId());
//        				shequ.setUnitName(unit.getName());
//        				sugarList1.add(shequ);
//        			}
//        		}
//        	}
//        }
//
//    	orderByUnit(pressureList1);
//    	orderByUnit(sugarList1);
//    	orderByUnit(managePressuerList1);
//    	orderByUnit(manageSugarList1);
//
//    	map.put("V30009", pressureList1);
//    	map.put("V30010", sugarList1);
//    	map.put("V30004", managePressuerList1);
//    	map.put("V30005", manageSugarList1);
//    	return  CommonResponse.getSuccessResponse(map);
//    }


    @RequestMapping(value = "/search/all", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object searchAll(AnalysisRequest request, String year) {
        Calendar date = Calendar.getInstance();

        String currentYear = String.valueOf(date.get(Calendar.YEAR));
        Map<String, Object> map = new HashMap<>();
        List<Unit> units = DataConstantContainer.getCommunityList();
        List<EntityGongweiYearReport> pressureData = new ArrayList<>();
        List<EntityGongweiYearReport> sugarData = new ArrayList<>();
        String item1="";
        String item2="";
        if (StringUtils.equals(currentYear, year)) {
            item1 = analysisService.getTotalData("V30011");//管理人群血压(月报)
            item2 = analysisService.getTotalData("V30012");//管理人群糖尿病(月报)
        } else {
             item1 = analysisService.getTotalData("V30009");//管理人群血压
             item2 = analysisService.getTotalData("V30010");//管理人群糖尿病


        }
        if (!StringUtils.isEmpty(item1)) {
            List<EntityGongweiYearReport> managePressureList = toBeanList(item1, EntityGongweiYearReport.class);
            for (EntityGongweiYearReport shequ : managePressureList) {
                for (Unit unit : units) {
                    if (shequ.getUnitId().equals(unit.getSource().getGongweiCode())&&shequ.getYear().equals(year)) {
                        shequ.setUnitId(unit.getId());
                        shequ.setUnitName(unit.getName());
                        pressureData.add(shequ);
                    }
                }
            }
        }
        if (!StringUtils.isEmpty(item2)) {
            List<EntityGongweiYearReport> manageSugerList = toBeanList(item2, EntityGongweiYearReport.class);
            for (EntityGongweiYearReport shequ : manageSugerList) {
                for (Unit unit : units) {
                    if (shequ.getUnitId().equals(unit.getSource().getGongweiCode())&&shequ.getYear().equals(year)) {
                        shequ.setUnitId(unit.getId());
                        shequ.setUnitName(unit.getName());
                        sugarData.add(shequ);
                    }
                }
            }
        }

        orderByUnit(pressureData);
        orderByUnit(sugarData);

        map.put("V30009", pressureData);
        map.put("V30010", sugarData);
        return CommonResponse.getSuccessResponse(map);
    }


}

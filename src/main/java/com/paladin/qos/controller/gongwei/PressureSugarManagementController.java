package com.paladin.qos.controller.gongwei;


import com.alibaba.fastjson.JSON;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.analysis.DataConstantContainer;
import com.paladin.qos.analysis.DataConstantContainer.*;
import com.paladin.qos.controller.analysis.AnalysisRequest;
import com.paladin.qos.model.data.DataUnit;
import com.paladin.qos.model.gongwei.EntityGongwei;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
    
    @RequestMapping(value = "/search/all", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object searchAll(AnalysisRequest request) {
    	Map<String, Object> map = new HashMap<>();
    	List<Unit> units = DataConstantContainer.getCommunityList();
    	List<EntityGongwei> pressureList1=new ArrayList<EntityGongwei>();
    	List<EntityGongwei> sugarList1=new ArrayList<EntityGongwei>();
    	
    	List<EntityGongwei> managePressuerList1=new ArrayList<EntityGongwei>();
    	List<EntityGongwei> manageSugarList1=new ArrayList<EntityGongwei>();
    	
    	
    	String item = analysisService.getTotalData("V30009");//高血压管理数，高血压随访数
    	String item1 = analysisService.getTotalData("V30010");//糖尿病管理数，糖尿病随访数
    	
    	String item4 = analysisService.getTotalData("V30004");//管理人群血压
    	String item5 = analysisService.getTotalData("V30005");//管理人群糖尿病
    	
    	if(!StringUtils.isEmpty(item4)){
        	List<EntityGongwei> managePressuerList = toBeanList(item4,EntityGongwei.class);
        	for(EntityGongwei shequ:managePressuerList){
        		
        		for(Unit unit : units){
        			if(shequ.MANAGEDCENTERCODE.equals(unit.getSource().getGongweiCode())){
        				shequ.setUnitId(unit.getId());
        				shequ.setUnitName(unit.getName());
        				managePressuerList1.add(shequ);
        			}
        		}
        	}
    	}
    	if(!StringUtils.isEmpty(item5)){
        	List<EntityGongwei> manageSugarList = toBeanList(item5,EntityGongwei.class);
        	for(EntityGongwei shequ:manageSugarList){
        		
        		for(Unit unit : units){
        			if(shequ.MANAGEDCENTERCODE.equals(unit.getSource().getGongweiCode())){
        				shequ.setUnitId(unit.getId());
        				shequ.setUnitName(unit.getName());
        				manageSugarList1.add(shequ);
        			}
        		}
        	}
    	}
        if(!StringUtils.isEmpty(item)){
        	List<EntityGongwei> pressureList = toBeanList(item,EntityGongwei.class);
        	for(EntityGongwei shequ:pressureList){
        		
        		for(Unit unit : units){
        			if(shequ.MANAGEDCENTERCODE.equals(unit.getSource().getGongweiCode())){
        				shequ.setUnitId(unit.getId());
        				shequ.setUnitName(unit.getName());
        				pressureList1.add(shequ);
        			}
        		}
        	}
    	}
    	
        if(!StringUtils.isEmpty(item1)){
        	List<EntityGongwei> sugarList = toBeanList(item1,EntityGongwei.class);
        	for(EntityGongwei shequ:sugarList){
        		for(Unit unit : units){
        			if(shequ.MANAGEDCENTERCODE.equals(unit.getSource().getGongweiCode())){
        				shequ.setUnitId(unit.getId());
        				shequ.setUnitName(unit.getName());
        				sugarList1.add(shequ);
        			}
        		}
        	}
        }
    	
    	orderByUnit(pressureList1);
    	orderByUnit(sugarList1);
    	
    	map.put("V30009", pressureList1);
    	map.put("V30010", sugarList1);
    	map.put("V30004", managePressuerList1);
    	map.put("V30005", manageSugarList1);
    	return  CommonResponse.getSuccessResponse(map);
    }



}

package com.paladin.qos.controller.gongwei;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;
import com.paladin.qos.model.data.DataUnit;
import com.paladin.qos.service.analysis.AnalysisConstant;
import com.paladin.qos.service.data.DataUnitService;
import com.paladin.qos.service.data.vo.DataUnitVO;
import com.paladin.qos.service.gongwei.ArchivesManagementService;
import com.paladin.qos.service.gongwei.vo.ArchivesMonthsVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.analysis.DataByUnit;
import com.paladin.qos.analysis.DataConstantContainer;
import com.paladin.qos.analysis.DataConstantContainer.Unit;
import com.paladin.qos.controller.analysis.AnalysisRequest;
import com.paladin.qos.model.familydoctor.FamilyDoctorUnit;
import com.paladin.qos.model.gongwei.EntityGongwei;
import com.paladin.qos.service.analysis.AnalysisService;
import com.paladin.qos.service.analysis.data.DataCountUnit;
import com.paladin.qos.service.familydoctor.FamilyDoctorUnitService;
import com.paladin.qos.service.gongwei.vo.ArchivesManagementVO;

@Controller
@RequestMapping("/qos/gongwei/archives")
public class ArchivesController {

    @Autowired
    private AnalysisService analysisService;

    @Autowired
    private FamilyDoctorUnitService familyDoctorUnitService;

    @Autowired
    private DataUnitService dataUnitService;

    @Autowired
    private ArchivesManagementService archivesManagementService;

    @GetMapping("/index")
    public Object dataIndex(Model model) {
//		List<Integer> types=new ArrayList<>();
//		types.add(DataUnit.TYPE_COMMUNITY);
//		List<DataUnitVO> dataUnitVOList =dataUnitService.selectData(types);
//		Iterator<DataUnitVO> it=dataUnitVOList.iterator();
//		while(it.hasNext()){
//			DataUnitVO dataUnitVO=it.next();
//			if(StringUtils.equalsIgnoreCase("320583810343",dataUnitVO.getId())){
//				it.remove();
//			}
//		}
//		model.addAttribute("unit", dataUnitVOList);
        return "/qos/exhibition/archives_index";
    }

    @RequestMapping(value = "/search/archives/12month", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object searchArchives12Month() {
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
        List<ArchivesMonthsVO> archiveList = new ArrayList<>();

        for (Date date : result) {
            String strDate=sdf.format(date);
            long monthArchives=archivesManagementService.findArchivesNumber("22001",date);
            String[] monthDay = strDate.split("-");
            Integer month = Integer.valueOf(monthDay[0]);
            Integer day = Integer.valueOf(monthDay[1]);
            ArchivesMonthsVO archivesMonthsVO = archivesManagementService.find12MonthArchives("22001", month, day);
            if (null==archivesMonthsVO){
                ArchivesMonthsVO archivesMonthsVO1=new ArchivesMonthsVO();
                archivesMonthsVO1.setArchivesNumber(monthArchives);
                archivesMonthsVO=archivesMonthsVO1;
            }else{
                archivesMonthsVO.setArchivesNumber(archivesMonthsVO.getArchivesNumber()+monthArchives);
            }
            archivesMonthsVO.setDate(strDate);
            archivesMonthsVO.setCreateArchivesRate(archivesMonthsVO.getArchivesNumber()/(double)number);
            archiveList.add(archivesMonthsVO);
        }

        return CommonResponse.getSuccessResponse(archiveList);
    }


    @RequestMapping(value = "/search/archives/month", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object searchArchivesByMonth() {



        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);
        Date startDate = c.getTime();

        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date endDate = ca.getTime();


        List<DataCountUnit> totalArchives = analysisService.countTotalNumByUnit("22001", 2, startDate, endDate, AnalysisConstant.SPECIAL_UNITS_FUYOU);
        List<ArchivesManagementVO> archivesManagementVOList = new ArrayList<>();
        for (DataCountUnit dataCountUnit : totalArchives) {
            ArchivesManagementVO archivesManagementVO = new ArchivesManagementVO();
            archivesManagementVO.setUnitId(dataCountUnit.getUnitId());
            archivesManagementVO.setActiveArchivesNumber(dataCountUnit.getCount());
            archivesManagementVOList.add(archivesManagementVO);
        }
        return CommonResponse.getSuccessResponse(archivesManagementVOList);
    }


    @RequestMapping(value = "/search/all123", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object searchAll(AnalysisRequest request) {

        List<ArchivesManagementVO> archivesManagementVOList = new ArrayList<>();
        List<DataCountUnit> totalArchives = analysisService.countTotalNumByUnit("22001", 2, request.getStartTime(), request.getEndTime(), AnalysisConstant.SPECIAL_UNITS_FUYOU);
        List<DataCountUnit> eventArchives = analysisService.countEventNumByUnit("22002", 2, request.getStartTime(), request.getEndTime(), AnalysisConstant.SPECIAL_UNITS_FUYOU);
        List<FamilyDoctorUnit> familyDoctorUnits = familyDoctorUnitService.findAll();

        Map<String, Long> eventArchivesMap = eventArchives.stream().collect(Collectors.toMap(w -> w.getUnitId(), w -> w.getCount()));
        Map<String, Long> totalArchivesMap = totalArchives.stream().collect(Collectors.toMap(w -> w.getUnitId(), w -> w.getCount()));

        for (FamilyDoctorUnit familyDoctorUnit : familyDoctorUnits) {
            ArchivesManagementVO archivesManagementVO = new ArchivesManagementVO();
            String unitId = familyDoctorUnit.getId();
            if (!StringUtils.isEmpty(unitId)) {
                archivesManagementVO.setUnitId(unitId);
                archivesManagementVO.setActiveArchivesNumber(totalArchivesMap.get(unitId));
                archivesManagementVO.setPublicArchivesNumber(eventArchivesMap.get(unitId));
                BigDecimal value1 = new BigDecimal(familyDoctorUnit.getPopulation());
                BigDecimal value2 = new BigDecimal("10000");
                archivesManagementVO.setPeopleNumber((long) value1.multiply(value2).doubleValue());
                archivesManagementVOList.add(archivesManagementVO);
            }
        }

        return CommonResponse.getSuccessResponse(archivesManagementVOList);
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

    //常住居民数，健康档案数，档案公开数
	@RequestMapping(value = "/search/all", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object getTotalData(AnalysisRequest request,EntityGongwei entityGongwei) {
    	Map<String, Object> map = new HashMap<>();
    	List<Unit> units = DataConstantContainer.getCommunityList();
    	List<EntityGongwei> danganList1=new ArrayList<EntityGongwei>();
    	List<EntityGongwei> publicList1=new ArrayList<EntityGongwei>();
    	
    	String item = analysisService.getTotalData("V30001");
    	String item1 = analysisService.getTotalData("V30002");
        if(!StringUtils.isEmpty(item)){
        	List<EntityGongwei> danganList = toBeanList(item,EntityGongwei.class);
        	for(EntityGongwei shequ:danganList){
        		
        		for(Unit unit : units){
        			if(shequ.MANAGEDCENTERCODE.equals(unit.getSource().getGongweiCode())){
        				shequ.setUnitId(unit.getId());
        				shequ.setUnitName(unit.getName());
        				danganList1.add(shequ);
        			}
        		}
        	}
    	}
    	
        if(!StringUtils.isEmpty(item1)){
        	List<EntityGongwei> publicList = toBeanList(item1,EntityGongwei.class);
        	for(EntityGongwei shequ:publicList){
        		for(Unit unit : units){
        			if(shequ.MANAGEDCENTERCODE.equals(unit.getSource().getGongweiCode())){
        				shequ.setUnitId(unit.getId());
        				shequ.setUnitName(unit.getName());
        				publicList1.add(shequ);
        			}
        		}
        	}
        }
    	
    	orderByUnit(danganList1);
    	orderByUnit(publicList1);
    	
    	map.put("V30001", danganList1);
    	map.put("V30002", publicList1);
    	return  CommonResponse.getSuccessResponse(map);
    }
    
	
	//老年人体检率，老年人健康管理率
	@RequestMapping(value = "/search/oldPeople", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object getoldPeople(AnalysisRequest request,EntityGongwei entityGongwei) {
    	Map<String, Object> map = new HashMap<>();
    	List<Unit> units = DataConstantContainer.getCommunityList();
    	List<EntityGongwei> checkList1=new ArrayList<EntityGongwei>();
    	List<EntityGongwei> fullYearList1=new ArrayList<EntityGongwei>();
    	
    	String item = analysisService.getTotalData("V30003");//老年人数，老年人体检数
    	String item1 = analysisService.getTotalData("V30008");//老年人数，有完整年度体检的老年人数
        if(!StringUtils.isEmpty(item)){
        	List<EntityGongwei> checkList = toBeanList(item,EntityGongwei.class);
        	for(EntityGongwei shequ:checkList){
        		
        		for(Unit unit : units){
        			if(shequ.MANAGEDCENTERCODE.equals(unit.getSource().getGongweiCode())){
        				shequ.setUnitId(unit.getId());
        				shequ.setUnitName(unit.getName());
        				checkList1.add(shequ);
        			}
        		}
        	}
    	}
    	
        if(!StringUtils.isEmpty(item1)){
        	List<EntityGongwei> fullYearList = toBeanList(item1,EntityGongwei.class);
        	for(EntityGongwei shequ:fullYearList){
        		for(Unit unit : units){
        			if(shequ.MANAGEDCENTERCODE.equals(unit.getSource().getGongweiCode())){
        				shequ.setUnitId(unit.getId());
        				shequ.setUnitName(unit.getName());
        				fullYearList1.add(shequ);
        			}
        		}
        	}
        }
    	
    	orderByUnit(checkList1);
    	orderByUnit(fullYearList1);
    	
    	map.put("V30003", checkList1);
    	map.put("V30008", fullYearList1);
    	return  CommonResponse.getSuccessResponse(map);
    }
	
    
}

package com.paladin.qos.controller.analysis;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.analysis.DataConstantContainer;
import com.paladin.qos.analysis.DataConstantContainer.Event;
import com.paladin.qos.model.data.DataEvent;
import com.paladin.qos.model.gongwei.EntityGongwei;
import com.paladin.qos.service.analysis.AnalysisService;
import com.paladin.qos.service.analysis.data.DataCountUnit;
import com.paladin.qos.service.data.DataUnitService;

import freemarker.template.utility.CollectionUtils;

@Controller
@RequestMapping("/qos/performance")
public class PerformanceController {
	@Autowired
    private DataUnitService dataUnitService;

    @Autowired
    private AnalysisService analysisService;
    
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
    
    public static final Map<String,String> userNameMap;
	static{
		userNameMap = new HashMap<String, String>();
		userNameMap.put("KSDYRMYY", "320583467170249");
		userNameMap.put("KSDERMYY", "320583467170513");
		userNameMap.put("KSZYY", "320583467170257");
	   	userNameMap.put("KSDSRMYY", "320583467170265");
	   	userNameMap.put("KSJXRMYY", "320583467170337");
	   	userNameMap.put("KSQDRMYY", "320583467170345");
	   	userNameMap.put("KSBCRMYY", "320583467170353");
	   	userNameMap.put("KSDSIRMYY", "320583467170361");
	   	userNameMap.put("KSZSRMYY", "320583467170417");
	   	userNameMap.put("KSHQRMYY", "320583467170441");
	   	userNameMap.put("KSDSHRMYY", "320583467170468");
	   	userNameMap.put("KSDLRMYY", "320583467170476");
	   	userNameMap.put("KSZZRMYY", "320583467170505");
	}
    
    private static final Map<String, String> codeMap;
     static {
    	 codeMap= new HashMap<>();
    	 codeMap.put("320024581", "320583467170249");
    	 codeMap.put("320024373", "320583467170513");
    	 codeMap.put("320024301", "320583467170257");
    	 codeMap.put("320024570", "320583467170265");
    	 codeMap.put("320016170", "320583467170337");
    	 codeMap.put("320011799", "320583467170345");
    	 codeMap.put("320016169", "320583467170353");
    	 codeMap.put("320016167", "320583467170361");
    	 codeMap.put("320016173", "320583467170417");
    	 codeMap.put("320016171", "320583467170441");
    	 codeMap.put("320016172", "320583467170468");
    	 codeMap.put("320016168", "320583467170476");
    	 codeMap.put("320016174", "320583467170505");
    }
    
 // 单位排序
 	private void orderByUnit(List<DataCountUnit> list) {
 		if (list != null && list.size() > 0) {
 			Collections.sort(list, new Comparator<DataCountUnit>() {
 				@Override
 				public int compare(DataCountUnit o1, DataCountUnit o2) {
 					String uid1 = o1.getUnitId();
 					String uid2 = o2.getUnitId();
 					return DataConstantContainer.getUnit(uid1).getOrderNum() > DataConstantContainer.getUnit(uid2).getOrderNum() ? 1 : -1;
 				}
 			});
 		}
 	}
 	private int getUnitType(Event event) {
		int targetType = event.getTargetType();
		if (targetType == DataEvent.TARGET_TYPE_COMMUNITY)
			return 2;
		if (targetType == DataEvent.TARGET_TYPE_HOSPITAL)
			return 1;
		return 0;
	}
 	@RequestMapping(value = "/search/all", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object searchAll(AnalysisRequest request,String userName) {
    	
 		Date startDate = request.getStartTime();
		Date endDate = request.getEndTime();
		
		Calendar c = Calendar.getInstance();
		c.setTime(startDate);
        c.add(Calendar.YEAR, -1);
        Date startDateLastYear = c.getTime();
        
        Calendar d = Calendar.getInstance();
		d.setTime(endDate);
        d.add(Calendar.YEAR, -1);
        Date endDateLastYear = d.getTime();
        List<String> userNameList = new ArrayList<String>();
		userNameList.add("KSDYRMYY");userNameList.add("KSDERMYY");userNameList.add("KSZYY");userNameList.add("KSDSRMYY");
		userNameList.add("KSJXRMYY");userNameList.add("KSQDRMYY");userNameList.add("KSBCRMYY");userNameList.add("KSDSIRMYY");
		userNameList.add("KSZSRMYY");userNameList.add("KSHQRMYY");userNameList.add("KSDSHRMYY");userNameList.add("KSDLRMYY");
		userNameList.add("KSZZRMYY");
        
		List<String> ignoreUnitIds = request.getIgnoreUnitIds();
		Map<String, Object> map = new HashMap<>();
		List<String> eventIds = request.getEventIds();
		if(StringUtils.isEmpty(userName)||!userNameList.contains(userName)){
			if (eventIds != null && eventIds.size() > 0) {
				
				for (String eventId : eventIds) {
					Event event = DataConstantContainer.getEvent(eventId);
					if (event != null) {
						int eventType = event.getEventType();
						int unitType = getUnitType(event);
						if (DataEvent.EVENT_TYPE_COUNT == eventType) {
							if(eventId.equals("60006")){
								Object item = analysisService.countTotalNumByUnit(eventId, unitType, startDateLastYear, endDateLastYear, ignoreUnitIds);
								map.put("6000601", item);//去年同期总收入
							}
							if(eventId.equals("60002")){
								Object item = analysisService.countTotalNumByUnit(eventId, unitType, startDateLastYear, endDateLastYear, ignoreUnitIds);
								map.put("6000201", item);//去年同期门诊总收入
							}
							
							if(eventId.equals("60011")){
								Object item = analysisService.countTotalNumByUnit(eventId, unitType, startDateLastYear, endDateLastYear, ignoreUnitIds);
								map.put("6001101", item);//去年同期门诊人次数
							}
							
							if(eventId.equals("60014")){
								Object item = analysisService.countTotalNumByUnit(eventId, unitType, startDateLastYear, endDateLastYear, ignoreUnitIds);
								map.put("6001401", item);//去年出院患者关联的药品总费用
							}
							
							if(eventId.equals("60015")){
								Object item = analysisService.countTotalNumByUnit(eventId, unitType, startDateLastYear, endDateLastYear, ignoreUnitIds);
								map.put("6001501", item);//去年出院患者关联的住院总费用
							}
							
							if(eventId.equals("51003")){
								Object item = analysisService.countTotalNumByUnit(eventId, unitType, startDateLastYear, endDateLastYear, ignoreUnitIds);
								map.put("5100301", item);//去年同期出院
							}
							
							Object item = analysisService.countTotalNumByUnit(eventId, unitType, startDate, endDate, ignoreUnitIds);
							if (item != null) {
								map.put(eventId, item);
							}
						} else if (DataEvent.EVENT_TYPE_RATE == eventType) {//42004
							Object item = analysisService.getAnalysisResultByUnit(eventId, unitType, startDate, endDate, ignoreUnitIds);
							if (item != null) {
								map.put(eventId, item);
							}
						}
					}
				}
				//执业医师数 --V90006
				String item1 = analysisService.getTotalData("V90006");
				List<DataCountUnit> physicianList = toBeanList(item1,DataCountUnit.class);
				if(physicianList!= null){
					for(DataCountUnit a:physicianList){
						a.setUnitId(codeMap.get(a.getUnitId()));
					}
					orderByUnit(physicianList);
					map.put("V90006", physicianList);
				}
				
				return CommonResponse.getSuccessResponse(map);
			}
		}else{
			if (eventIds != null && eventIds.size() > 0) {
				
				for (String eventId : eventIds) {
					Event event = DataConstantContainer.getEvent(eventId);
					if (event != null) {
						int eventType = event.getEventType();
						int unitType = getUnitType(event);
						if (DataEvent.EVENT_TYPE_COUNT == eventType) {
							if(eventId.equals("60006")){
								Object item = analysisService.countTotalNumByUnitAndUserName(eventId, unitType, startDateLastYear, endDateLastYear, ignoreUnitIds,userNameMap.get(userName));
								map.put("6000601", item);//去年同期总收入
							}
							if(eventId.equals("60002")){
								Object item = analysisService.countTotalNumByUnitAndUserName(eventId, unitType, startDateLastYear, endDateLastYear, ignoreUnitIds,userNameMap.get(userName));
								map.put("6000201", item);//去年同期门诊总收入
							}
							
							if(eventId.equals("60011")){
								Object item = analysisService.countTotalNumByUnitAndUserName(eventId, unitType, startDateLastYear, endDateLastYear, ignoreUnitIds,userNameMap.get(userName));
								map.put("6001101", item);//去年同期门诊人次数
							}
							
							if(eventId.equals("60014")){
								Object item = analysisService.countTotalNumByUnitAndUserName(eventId, unitType, startDateLastYear, endDateLastYear, ignoreUnitIds,userNameMap.get(userName));
								map.put("6001401", item);//去年出院患者关联的药品总费用
							}
							
							if(eventId.equals("60015")){
								Object item = analysisService.countTotalNumByUnitAndUserName(eventId, unitType, startDateLastYear, endDateLastYear, ignoreUnitIds,userNameMap.get(userName));
								map.put("6001501", item);//去年出院患者关联的住院总费用
							}
							
							if(eventId.equals("51003")){
								Object item = analysisService.countTotalNumByUnitAndUserName(eventId, unitType, startDateLastYear, endDateLastYear, ignoreUnitIds,userNameMap.get(userName));
								map.put("5100301", item);//去年同期出院
							}
							
							Object item = analysisService.countTotalNumByUnitAndUserName(eventId, unitType, startDate, endDate, ignoreUnitIds,userNameMap.get(userName));
							if (item != null) {
								map.put(eventId, item);
							}
						} else if (DataEvent.EVENT_TYPE_RATE == eventType) {//42004
							Object item = analysisService.getAnalysisResultByUnitAndUserName(eventId, unitType, startDate, endDate, ignoreUnitIds,userNameMap.get(userName));
							if (item != null) {
								map.put(eventId, item);
							}
						}
					}
				}
				//执业医师数 --V90006
				String item1 = analysisService.getTotalData("V90006");
				List<DataCountUnit> physicianList = toBeanList(item1,DataCountUnit.class);
				List<DataCountUnit> physicianList1 = new ArrayList<DataCountUnit>();
				if(physicianList!= null){
					for(DataCountUnit a:physicianList){
						if(codeMap.get(a.getUnitId()).equals(userNameMap.get(userName))){
							a.setUnitId(codeMap.get(a.getUnitId()));
							physicianList1.add(a);
						}
						
					}
					orderByUnit(physicianList1);
					map.put("V90006", physicianList1);
				}
				
				return CommonResponse.getSuccessResponse(map);
			}
		}
		 
		return CommonResponse.getErrorResponse();
    }
}

package com.paladin.qos.controller;

import com.alibaba.fastjson.JSON;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.analysis.DataConstantContainer;
import com.paladin.qos.analysis.DataConstantContainer.*;
import com.paladin.qos.analysis.TimeUtil;
import com.paladin.qos.controller.analysis.AnalysisRequest;
import com.paladin.qos.model.data.DataEvent;
import com.paladin.qos.model.familydoctor.FamilyDoctorUnit;
import com.paladin.qos.model.gongwei.AddressEntity;
import com.paladin.qos.model.gongwei.Disease;
import com.paladin.qos.model.gongwei.EntityGongwei;
import com.paladin.qos.model.gongwei.EntityGongweiFamily;
import com.paladin.qos.model.home.Sign;
import com.paladin.qos.model.register.Register;
import com.paladin.qos.service.analysis.AnalysisService;
import com.paladin.qos.service.analysis.data.DataCountUnit;
import com.paladin.qos.service.familydoctor.FamilyDoctorPersonnelService;
import com.paladin.qos.service.familydoctor.FamilyDoctorUnitService;
import com.paladin.qos.service.gongwei.ArchivesManagementService;

import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import java.util.*;

/**
 * @author MyKite
 * @version 2019年6月24日 下午3:04:58
 */
@Controller
@RequestMapping("/home/page/qos")
public class HomePageController {

    @Autowired
    private AnalysisService analysisService;

    @Autowired
    private FamilyDoctorPersonnelService familyDoctorPersonnelService;

    @Autowired
    private FamilyDoctorUnitService familyDoctorUnitService;
    
    @Autowired
    private ArchivesManagementService archivesManagementService;

    @RequestMapping(value = "/index")
    public String homeIndex(HttpServletRequest request) {
        return "/qos/homepage/index";
    }


    @RequestMapping(value = "/familydoctor")
    public String familyIndex(HttpServletRequest request) {
        return "/qos/homepage/familydoctor/index";
    }


    @RequestMapping(value = "/publichealth/{name}")
    public String publichealthIndex(@PathVariable("name") String name) {
        return "/qos/homepage/publichealth/" + name;
    }


    @RequestMapping(value = "/quailtydisplay")
    public String quailtydisplayIndex(HttpServletRequest request) {
        return "/qos/homepage/quailtydisplay/index";
    }

    /**
     * 患者列表
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/quailtydisplay/getRegisterList", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object getRegisterList(String id) {
        List<Register> registerList = analysisService.findRegisterList();
        Collections.reverse(registerList);
        return CommonResponse.getSuccessResponse(registerList);
    }

    /**
     * 社区当天门诊人次，社区当天急诊人次，当月门诊人次，当月急诊人次
     */
    @RequestMapping(value = "/quailtydisplay/getOutPatientNumber", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object getOutPatientNumber() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<DataCountUnit> patientNumberList = analysisService.getOutPatientNumber();//当天门诊人次
        List<DataCountUnit> emergencyNumberList = analysisService.getEmergencyNumber();//当天急诊人次
        List<DataCountUnit> todayNumberList = analysisService.getTodayNumber();//当月门诊人次
        List<DataCountUnit> thisMonthNumberList = analysisService.getThisMonthNumber();//当月急诊人次
        map.put("patientNumberList", patientNumberList);
        map.put("emergencyNumberList", emergencyNumberList);
        map.put("todayNumberList", todayNumberList);
        map.put("thisMonthNumberList", thisMonthNumberList);

        return CommonResponse.getSuccessResponse(map);
    }


    /**
     * 医院当天门诊人次，医院当天急诊人次，当月门诊人次，当月急诊人次
     */
    @RequestMapping(value = "/hospital/getOutPatientNumber", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object getHospitalOutPatientNumber() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<DataCountUnit> patientNumberList = analysisService.getHospitalOutPatientNumber();//当天门诊人次
        List<DataCountUnit> emergencyNumberList = analysisService.getHospitalEmergencyNumber();//当天急诊人次
        List<DataCountUnit> todayNumberList = analysisService.getHospitalTodayNumber();//当月门诊人次
        List<DataCountUnit> thisMonthNumberList = analysisService.getHospitalThisMonthNumber();//当月急诊人次
        map.put("patientHospitalNumberList", patientNumberList);
        map.put("emergencyHospitalNumberList", emergencyNumberList);
        map.put("todayNumberHospitalList", todayNumberList);
        map.put("thisMonthNumberHospitalList", thisMonthNumberList);

        return CommonResponse.getSuccessResponse(map);
    }

    @RequestMapping(value = "/population/signing", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object populationSigningNum() {
        return CommonResponse.getSuccessResponse(analysisService.populationSigningNum());
    }

    //门急诊折线图社区（今年，去年）13003
    @RequestMapping(value = "/data/get/month/twoYear", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object getTwoYear() {
        return CommonResponse.getSuccessResponse(analysisService.getTwoYear());
    }

    private int getUnitType(Event event) {
        int targetType = event.getTargetType();
        if (targetType == DataEvent.TARGET_TYPE_COMMUNITY)
            return 2;
        if (targetType == DataEvent.TARGET_TYPE_HOSPITAL)
            return 1;
        return 0;
    }

    /**
     * 签约列表
     *
     * @return
     */
    @RequestMapping(value = "/quailtydisplay/getSignList", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object getSignList() {
        String item = analysisService.getTotalData("V70000");
        List<Sign> signs=toBeanList(item,Sign.class);
        return CommonResponse.getSuccessResponse(signs);
    }

    //根据event获取数据
    @RequestMapping(value = "/data/get/total", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object getProcessedDataTotal(AnalysisRequest request) {
        Date startDate = request.getStartTime();
        Date endDate = request.getEndTime();
        List<String> ignoreUnitIds = request.getIgnoreUnitIds();

        List<String> eventIds = request.getEventIds();

        if (eventIds != null && eventIds.size() > 0) {
            Map<String, Long> map = new HashMap<>();
            for (String eventId : eventIds) {
                DataConstantContainer.Event event = DataConstantContainer.getEvent(eventId);
                if (event != null) {
                    long count = analysisService.getTotalNumOfEvent(eventId, startDate, endDate, ignoreUnitIds);
                    map.put(eventId, count);
                }
            }
            return CommonResponse.getSuccessResponse(map);
        } else {
            String eventId = request.getEventId();
            return CommonResponse.getSuccessResponse(analysisService.getTotalNumOfEvent(eventId, startDate, endDate, ignoreUnitIds));
        }
    }


    //获取团队信息
    @RequestMapping(value = "/team/find/all", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object DataFindAll() {
        return CommonResponse.getSuccessResponse(familyDoctorPersonnelService.selectFindAll());
    }


    //按机构取当月（预约挂号数量41001，总诊疗人次数41002，检查人次数41003，检验人次数41004，）
    @RequestMapping(value = "/data/get/unit", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object getProcessedDataByUnit(AnalysisRequest request) {
        
        List<String> ignoreUnitIds = request.getIgnoreUnitIds();

        List<String> eventIds = request.getEventIds();
        if (eventIds != null && eventIds.size() > 0) {
            Map<String, Object> map = new HashMap<>();
            for (String eventId : eventIds) {
            	if(eventId.equals("41005")){
            		Date startDate = TimeUtil.ThirtyOneBefore();
                    Date endDate = new Date();
            		 Event event = DataConstantContainer.getEvent(eventId);
                     if (event != null) {
                         int eventType = event.getEventType();
                         int unitType = getUnitType(event);
                         if (DataEvent.EVENT_TYPE_COUNT == eventType) {
                             Object item = analysisService.countTotalNumByUnit(eventId, unitType, startDate, endDate, ignoreUnitIds);
                             if (item != null) {
                                 map.put(eventId, item);
                             }
                         } else if (DataEvent.EVENT_TYPE_RATE == eventType) {
                             Object item = analysisService.getAnalysisResultByUnit(eventId, unitType, startDate, endDate, ignoreUnitIds);
                             if (item != null) {
                                 map.put(eventId, item);
                             }
                         }
                     }
            	}else{
            		Date startDate = TimeUtil.FirstDayOfThisMonth();
                    Date endDate = new Date();
            		Event event = DataConstantContainer.getEvent(eventId);
                    if (event != null) {
                        int eventType = event.getEventType();
                        int unitType = getUnitType(event);
                        if (DataEvent.EVENT_TYPE_COUNT == eventType) {
                            Object item = analysisService.countTotalNumByUnit(eventId, unitType, startDate, endDate, ignoreUnitIds);
                            if (item != null) {
                                map.put(eventId, item);
                            }
                        } else if (DataEvent.EVENT_TYPE_RATE == eventType) {
                            Object item = analysisService.getAnalysisResultByUnit(eventId, unitType, startDate, endDate, ignoreUnitIds);
                            if (item != null) {
                                map.put(eventId, item);
                            }
                        }
                    }
            	}
            	
                
            }
            return CommonResponse.getSuccessResponse(map);
        } 
        return CommonResponse.getErrorResponse();
    }


    //大数据页面 按机构
    @RequestMapping(value = "/data/display/unit", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object getDataByUnit(AnalysisRequest request) {
        Date startDate = request.getStartTime();
        Date endDate = request.getEndTime();
        List<String> ignoreUnitIds = request.getIgnoreUnitIds();

        List<String> eventIds = request.getEventIds();

            Map<String, Object> map = new HashMap<>();
            for (String eventId : eventIds) {
                Event event = DataConstantContainer.getEvent(eventId);
                if (event != null) {
                    int eventType = event.getEventType();
                    int unitType = getUnitType(event);
                    if (DataEvent.EVENT_TYPE_COUNT == eventType) {
                        Object item = analysisService.countTotalNumByUnit(eventId, unitType, startDate, endDate, ignoreUnitIds);
                        if (item != null) {
                            map.put(eventId, item);
                        }
                    } else if (DataEvent.EVENT_TYPE_RATE == eventType) {
                        Object item = analysisService.getAnalysisResultByUnit(eventId, unitType, startDate, endDate, ignoreUnitIds);
                        if (item != null) {
                            map.put(eventId, item);
                        }
                    }
                }
            }
            return CommonResponse.getSuccessResponse(map);
    }

    //医院平均住院日41005 取前31天
    @RequestMapping(value = "/data/get/unit/hospital", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object getProcessedHospitalDataByUnit(AnalysisRequest request) {
        Date startDate = TimeUtil.ThirtyOneBefore();
        Date endDate = new Date();
        List<String> ignoreUnitIds = request.getIgnoreUnitIds();

        List<String> eventIds = request.getEventIds();
        if (eventIds != null && eventIds.size() > 0) {
            Map<String, Object> map = new HashMap<>();
            for (String eventId : eventIds) {
                Event event = DataConstantContainer.getEvent(eventId);
                if (event != null) {
                    int eventType = event.getEventType();
                    int unitType = getUnitType(event);
                    if (DataEvent.EVENT_TYPE_COUNT == eventType) {
                        Object item = analysisService.countTotalNumByUnit(eventId, unitType, startDate, endDate, ignoreUnitIds);
                        if (item != null) {
                            map.put(eventId, item);
                        }
                    } else if (DataEvent.EVENT_TYPE_RATE == eventType) {
                        Object item = analysisService.getAnalysisResultByUnit(eventId, unitType, startDate, endDate, ignoreUnitIds);
                        if (item != null) {
                            map.put(eventId, item);
                        }
                    }
                }
            }
            return CommonResponse.getSuccessResponse(map);
        } else {
            String eventId = request.getEventId();
            Event event = DataConstantContainer.getEvent(eventId);
            if (event != null) {
                int eventType = event.getEventType();
                int unitType = getUnitType(event);
                if (DataEvent.EVENT_TYPE_COUNT == eventType) {
                    return CommonResponse.getSuccessResponse(analysisService.countTotalNumByUnit(eventId, unitType, startDate, endDate, ignoreUnitIds));
                } else if (DataEvent.EVENT_TYPE_RATE == eventType) {
                    return CommonResponse.getSuccessResponse(analysisService.getAnalysisResultByUnit(eventId, unitType, startDate, endDate, ignoreUnitIds));
                }
            }
        }
        return CommonResponse.getErrorResponse();
    }

    @PostMapping("/data/get/day/instalments")
    @ResponseBody
    public Object getDataOfDayByInstalments(AnalysisRequest request) {
        List<String> eventIds = request.getEventIds();
        Date startDate = request.getStartTime();
        Date endDate = request.getEndTime();
        boolean byUnit = request.getByUnit() == 1;
        List<String> ignoreUnitIds = request.getIgnoreUnitIds();

        if (eventIds != null && eventIds.size() > 0) {
            Map<String, Object> map = new HashMap<>();
            for (String eventId : eventIds) {
                Event event = DataConstantContainer.getEvent(eventId);
                if (event != null) {
                    int unitType = getUnitType(event);
                    Object item = byUnit ? analysisService.getDataSetOfDay(eventId, unitType, startDate, endDate, ignoreUnitIds)
                            : analysisService.getAnalysisResultByDay(eventId, unitType, startDate, endDate, ignoreUnitIds);
                    if (item != null) {
                        map.put(eventId, item);
                    }
                }
            }
            return CommonResponse.getSuccessResponse(map);
        } else {
            String eventId = request.getEventId();
            return CommonResponse.getSuccessResponse(analysisService.getDataSetOfDay(eventId, startDate, endDate, ignoreUnitIds));
        }
    }


    @PostMapping("/data/get/month/instalments")
    @ResponseBody
    public Object getDataOfMonthByInstalments(AnalysisRequest request) {
        List<String> eventIds = request.getEventIds();
        Date startDate = request.getStartTime();
        Date endDate = request.getEndTime();
        boolean byUnit = request.getByUnit() == 1;
        List<String> ignoreUnitIds = request.getIgnoreUnitIds();

        if (eventIds != null && eventIds.size() > 0) {
            Map<String, Object> map = new HashMap<>();
            for (String eventId : eventIds) {
                Event event = DataConstantContainer.getEvent(eventId);
                if (event != null) {
                    int unitType = getUnitType(event);
                    Object item = byUnit ? analysisService.getDataSetOfMonth(eventId, unitType, startDate, endDate, ignoreUnitIds)
                            : analysisService.getAnalysisResultByMonth(eventId, unitType, startDate, endDate, ignoreUnitIds);
                    if (item != null) {
                        map.put(eventId, item);
                    }
                }
            }
            return CommonResponse.getSuccessResponse(map);
        } else {
            String eventId = request.getEventId();
            return CommonResponse.getSuccessResponse(analysisService.getDataSetOfMonth(eventId, startDate, endDate, ignoreUnitIds));
        }
    }

    @PostMapping("/data/get/year/instalments")
    @ResponseBody
    public Object getDataOfYearByInstalments(AnalysisRequest request) {
        Date startDate = request.getStartTime();
        Date endDate = request.getEndTime();
        int startYear = TimeUtil.getYear(startDate);
        int endYear = TimeUtil.getYear(endDate);
        boolean byUnit = request.getByUnit() == 1;
        List<String> ignoreUnitIds = request.getIgnoreUnitIds();

        List<String> eventIds = request.getEventIds();
        if (eventIds != null && eventIds.size() > 0) {
            Map<String, Object> map = new HashMap<>();
            for (String eventId : eventIds) {
                Event event = DataConstantContainer.getEvent(eventId);
                if (event != null) {
                    int unitType = getUnitType(event);
                    Object item = byUnit ? analysisService.getDataSetOfYear(eventId, unitType, startYear, endYear, ignoreUnitIds)
                            : analysisService.getAnalysisResultByYear(eventId, unitType, startYear, endYear, ignoreUnitIds);
                    if (item != null) {
                        map.put(eventId, item);
                    }
                }
            }
            return CommonResponse.getSuccessResponse(map);
        } else {
            String eventId = request.getEventId();
            return CommonResponse.getSuccessResponse(analysisService.getDataSetOfYear(eventId, startYear, endYear, ignoreUnitIds));
        }
    }

    @RequestMapping(value = "/get/total/data", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object getTotalData(AnalysisRequest request) {
        List<Unit> units = DataConstantContainer.getCommunityList();
        HashMap unitMap = new HashMap();

        for (Unit unit : units) {
            Unit u = DataConstantContainer.getUnit(unit.getId());
            String result = null;
            if (null != u) {
                result = u.getSource().getGongweiCode();
                if (null != result)
                    unitMap.put(result, unit.getName());
            }

        }
        List<String> eventIds = request.getEventIds();
        if (eventIds != null && eventIds.size() > 0) {
            Map<String, Object> map = new HashMap<>();
            for (String eventId : eventIds) {
                Object item = analysisService.getTotalData(eventId);
                map.put(eventId, item);
            }
            return CommonResponse.getSuccessResponse(map.toString(), unitMap);
        } else
            return null;
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

    @RequestMapping(value = "/get/family/data", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object getFamilyData(AnalysisRequest request) {
        List<String> eventIds = request.getEventIds();
        Map<String, Object> map = new HashMap<>();
        if (eventIds != null && eventIds.size() > 0) {
            List<String> items=new ArrayList<>();
            for (String eventId : eventIds) {
                String item = analysisService.getTotalData(eventId);
                List<EntityGongweiFamily> list = toBeanList(item,EntityGongweiFamily.class);
                if (!CollectionUtils.isEmpty(list)){
                    map.put(eventId,list.get(0).getCount());
                }else{
                    map.put(eventId,0);
                }
            }
            return CommonResponse.getSuccessResponse(map);
        } else
            return null;
    }




    // 单位排序
 	private void orderByUnit(List<EntityGongwei> list) {
 		if (list != null && list.size() > 0) {
 			Collections.sort(list, new Comparator<EntityGongwei>() {
 				@Override
 				public int compare(EntityGongwei o1, EntityGongwei o2) {
 					String uid1 = o1.getUnitId();
 					String uid2 = o2.getUnitId();
 					return DataConstantContainer.getUnit(uid1).getOrderNum() > DataConstantContainer.getUnit(uid2).getOrderNum() ? 1 : -1;
 				}
 			});
 		}
 	}
    //高血压糖尿病情况报表
    @RequestMapping(value = "/pressureAndSugar", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object searchAll(AnalysisRequest request,String year) {
    	Map<String, Object> map = new HashMap<>();
    	List<Unit> units = DataConstantContainer.getCommunityList();
    	List<EntityGongwei> pressureList1=new ArrayList<EntityGongwei>();
    	List<EntityGongwei> sugarList1=new ArrayList<EntityGongwei>();
    	
    	List<EntityGongwei> managePressuerList1=new ArrayList<EntityGongwei>();
    	List<EntityGongwei> manageSugarList1=new ArrayList<EntityGongwei>();
    	
    	
    	List<EntityGongwei> pressureList = analysisService.getPressureTotalDataFromLocal(year);//高血压管理数，高血压随访数
    	List<EntityGongwei> sugarList = analysisService.getSugarTotalDataFromLocal(year);//糖尿病管理数，糖尿病随访数
    	
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
        if(pressureList!=null){
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
    	
        if(sugarList!=null){
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
    	orderByUnit(managePressuerList1);
    	orderByUnit(manageSugarList1);
    	
    	map.put("V30009", pressureList1);
    	map.put("V30010", sugarList1);
    	map.put("V30004", managePressuerList1);
    	map.put("V30005", manageSugarList1);
    	return  CommonResponse.getSuccessResponse(map);
    }
    
    @RequestMapping(value = "/getTop10Disease", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Object getTop5Disease(AnalysisRequest request) {
    	List<Unit> units = DataConstantContainer.getHospitalList();
    	
    	
    	List<String> eventIds = request.getEventIds();
    	Map<String, Object> map = new HashMap<>();
    	for(String eventId : eventIds){
    		List<Disease> diseaseList1=new ArrayList<Disease>();
    		String item = analysisService.getTotalData(eventId);//疾病top5
        	if(!StringUtils.isEmpty(item)){
            	List<Disease> diseaseList = toBeanList(item,Disease.class);
            	for(Disease yiyuan:diseaseList){
            		for(Unit unit : units){
            			if(yiyuan.orgcode.equals(unit.getSource().getId())){
            				yiyuan.setUnitId(unit.getId());
            				yiyuan.setUnitName(unit.getName());
            				diseaseList1.add(yiyuan);
            			}
            		}
            	}
        	}
        	
        	List<Disease> nameList = analysisService.findNameList();
        	List<Disease> diseaseList2=new ArrayList<Disease>();
        	for(Disease yiyuan:diseaseList1){
        		for(Disease name:nameList){
        			if((yiyuan.getDiseasecode()).contains(".")){
        				if((yiyuan.getDiseasecode()+"00").equals(name.getDiseasecode())){
            				yiyuan.setDiseasecodeName(name.getDiseasecodeName());
            				diseaseList2.add(yiyuan);
            			}
        			}else{
        				if((yiyuan.getDiseasecode()+"0").equals(name.getDiseasecode())){
            				yiyuan.setDiseasecodeName(name.getDiseasecodeName());
            				diseaseList2.add(yiyuan);
            			}
        			}
        			
        		}
        	}
        	
        	if(diseaseList2.size()>10){
        		map.put(eventId, diseaseList2.subList(0, 10));
        	}else{
        		map.put(eventId, diseaseList1);
        	}
        	
    	}
    	
		return CommonResponse.getSuccessResponse(map);
	}

    
    //todo居住地址统计
    @RequestMapping(value="/getAddressCount", method = { RequestMethod.GET, RequestMethod.POST })
   	@ResponseBody
   	public Object getAddressCount(AnalysisRequest request) {
    	List<String> eventIds = new ArrayList<String>();
    	eventIds.add("V80001");eventIds.add("V80002");eventIds.add("V80003");eventIds.add("V80004");eventIds.add("V80005");eventIds.add("V80006");
    	eventIds.add("V80007");eventIds.add("V80008");eventIds.add("V80009");eventIds.add("V80010");eventIds.add("V80011");eventIds.add("V80012");
    	eventIds.add("V80013");
    	
    	List<String> total = new ArrayList<String>();
        long gaoxinqu = 0;long bacheng = 0;long huaqiao = 0;long zhoushi = 0;long qiandeng = 0;
        long lujia = 0;long zhangpu = 0;long zhouzhuang = 0;long jinxi = 0;long dianshanhu = 0;
        long kaifaqu = 0;
		for (String eventId : eventIds) {
					String item = analysisService.getTotalData(eventId);
					List<AddressEntity> addressList = toBeanList(item,AddressEntity.class);
					for(AddressEntity address:addressList){
						if("001".equals(address.getCode())){
							gaoxinqu+=address.getCount();
						}else if("002".equals(address.getCode())){
							bacheng+=address.getCount();
						}else if("003".equals(address.getCode())){
							huaqiao+=address.getCount();
						}else if("004".equals(address.getCode())){
							zhoushi+=address.getCount();
						}else if("005".equals(address.getCode())){
							qiandeng+=address.getCount();
						}else if("006".equals(address.getCode())){
							lujia+=address.getCount();
						}else if("007".equals(address.getCode())){
							zhangpu+=address.getCount();
						}else if("008".equals(address.getCode())){
							zhouzhuang+=address.getCount();
						}else if("009".equals(address.getCode())){
							jinxi+=address.getCount();
						}else if("010".equals(address.getCode())){
							dianshanhu+=address.getCount();
						}else if("011".equals(address.getCode())){
							kaifaqu+=address.getCount();
						}
					}
		}
		//社区总人口数
		List<FamilyDoctorUnit> list=familyDoctorUnitService.findAll();
		long gaoxinquTotal = 0;long bachengTotal = 0;long huaqiaoTotal = 0;long zhoushiTotal = 0;long qiandengTotal = 0;
        long lujiaTotal = 0;long zhangpuTotal = 0;long zhouzhuangTotal = 0;long jinxiTotal = 0;long dianshanhuTotal = 0;
        long kaifaquTotal = 0;
		for(FamilyDoctorUnit familyDoctorUnit:list){
			if(familyDoctorUnit.getId().equals("320583100467170433")){//蓬朗
				Float f = Float.parseFloat(familyDoctorUnit.getPopulation());
				kaifaquTotal+=(f*10000);            
			}else if(familyDoctorUnit.getId().equals("320583100PDY002111")){//震川
				Float f = Float.parseFloat(familyDoctorUnit.getPopulation());
				kaifaquTotal+=(f*10000/2);
				gaoxinquTotal+=(f*10000/2);          
			}else if(familyDoctorUnit.getId().equals("320583100PDY00212X")){//江浦
				Float f = Float.parseFloat(familyDoctorUnit.getPopulation());
				gaoxinquTotal+=(f*10000);          
			}else if(familyDoctorUnit.getId().equals("320583100PDY002138")){//亭林
				Float f = Float.parseFloat(familyDoctorUnit.getPopulation());
				gaoxinquTotal+=(f*10000);          
			}else if(familyDoctorUnit.getId().equals("320583101467170396")){//巴城
				Float f = Float.parseFloat(familyDoctorUnit.getPopulation());
				bachengTotal+=(f*10000);          
			}else if(familyDoctorUnit.getId().equals("320583102467170409")){//周市
				Float f = Float.parseFloat(familyDoctorUnit.getPopulation());
				zhoushiTotal+=(f*10000);         
			}else if(familyDoctorUnit.getId().equals("320583102PDY203693")){ //柏庐
				Float f = Float.parseFloat(familyDoctorUnit.getPopulation());
				gaoxinquTotal+=(f*10000);          
			}else if(familyDoctorUnit.getId().equals("320583103PDY004045")){//陆家
				Float f = Float.parseFloat(familyDoctorUnit.getPopulation());
				lujiaTotal+=(f*10000);          
			}else if(familyDoctorUnit.getId().equals("320583104PDY000765")){//花桥
				Float f = Float.parseFloat(familyDoctorUnit.getPopulation());
				huaqiaoTotal+=(f*10000);         
			}else if(familyDoctorUnit.getId().equals("320583105467170400")){//淀山湖
				Float f = Float.parseFloat(familyDoctorUnit.getPopulation());
				dianshanhuTotal+=(f*10000);         
			}else if(familyDoctorUnit.getId().equals("320583106PDY111254")){//张浦
				Float f = Float.parseFloat(familyDoctorUnit.getPopulation());
				zhangpuTotal+=(f*10000);          
			}else if(familyDoctorUnit.getId().equals("320583107467170500")){//周庄
				Float f = Float.parseFloat(familyDoctorUnit.getPopulation());
				zhouzhuangTotal+=(f*10000);          
			}else if(familyDoctorUnit.getId().equals("32058310846717045X")){//千灯
				Float f = Float.parseFloat(familyDoctorUnit.getPopulation());
				qiandengTotal+=(f*10000);         
			}else if(familyDoctorUnit.getId().equals("320583109467170300")){//锦溪
				Float f = Float.parseFloat(familyDoctorUnit.getPopulation());
				jinxiTotal+=(f*10000);         
			}else if(familyDoctorUnit.getId().equals("320583400PDY036125")){//青阳
				Float f = Float.parseFloat(familyDoctorUnit.getPopulation());
				kaifaquTotal+=(f*10000);
			}
		}
		
		Map<String, Float> map = new HashMap<>();
		map.put("高新区", (float)gaoxinqu/gaoxinquTotal);
		map.put("开发区", (float)kaifaqu/kaifaquTotal);
		map.put("周市", (float)zhoushi/zhoushiTotal);
		map.put("千灯", (float)qiandeng/qiandengTotal);
		map.put("张浦", (float)zhangpu/zhangpuTotal);
		map.put("巴城", (float)bacheng/bachengTotal);
		map.put("花桥", (float)huaqiao/huaqiaoTotal);
		map.put("陆家", (float)lujia/lujiaTotal);
		map.put("淀山湖", (float)dianshanhu/dianshanhuTotal);
		map.put("锦溪", (float)jinxi/jinxiTotal);
		map.put("周庄", (float)zhouzhuang/zhouzhuangTotal);
		return CommonResponse.getSuccessResponse(map);
    }
}


 
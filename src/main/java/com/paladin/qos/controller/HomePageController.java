package com.paladin.qos.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.paladin.qos.analysis.DataConstantContainer;
import com.paladin.qos.controller.analysis.AnalysisRequest;
import com.paladin.qos.model.data.DataEvent;
import com.paladin.qos.model.familydoctor.FamilyDoctorUnit;
import com.paladin.qos.model.home.Sign;
import com.paladin.qos.service.analysis.data.DataSigningMonth;
import com.paladin.qos.service.familydoctor.FamilyDoctorPersonnelService;
import com.paladin.qos.service.gongwei.ArchivesManagementService;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.analysis.DataConstantContainer;
import com.paladin.qos.analysis.DataConstantContainer.Event;
import com.paladin.qos.analysis.TimeUtil;
import com.paladin.qos.controller.analysis.AnalysisRequest;
import com.paladin.qos.model.data.DataEvent;
import com.paladin.qos.model.register.Register;
import com.paladin.qos.service.analysis.AnalysisService;
import com.paladin.qos.service.analysis.data.DataCountUnit;

/**
 * @author MyKite
 * @version 2019年6月24日 下午3:04:58
 */
@Controller
@RequestMapping("/home/page/qos")
public class HomePageController {

    @Autowired
    private AnalysisService registerService;

    @Autowired
    private FamilyDoctorPersonnelService familyDoctorPersonnelService;

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
        List<Register> registerList = registerService.findRegisterList();
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
        List<DataCountUnit> patientNumberList = registerService.getOutPatientNumber();//当天门诊人次
        List<DataCountUnit> emergencyNumberList = registerService.getEmergencyNumber();//当天急诊人次
        List<DataCountUnit> todayNumberList = registerService.getTodayNumber();//当月门诊人次
        List<DataCountUnit> thisMonthNumberList = registerService.getThisMonthNumber();//当月急诊人次
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
        List<DataCountUnit> patientNumberList = registerService.getHospitalOutPatientNumber();//当天门诊人次
        List<DataCountUnit> emergencyNumberList = registerService.getHospitalEmergencyNumber();//当天急诊人次
        List<DataCountUnit> todayNumberList = registerService.getHospitalTodayNumber();//当月门诊人次
        List<DataCountUnit> thisMonthNumberList = registerService.getHospitalThisMonthNumber();//当月急诊人次
        map.put("patientHospitalNumberList", patientNumberList);
        map.put("emergencyHospitalNumberList", emergencyNumberList);
        map.put("todayNumberHospitalList", todayNumberList);
        map.put("thisMonthNumberHospitalList", thisMonthNumberList);

        return CommonResponse.getSuccessResponse(map);
    }

    @RequestMapping(value = "/population/signing", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object populationSigningNum() {
        return CommonResponse.getSuccessResponse(registerService.populationSigningNum());
    }

    //门急诊折线图社区（今年，去年）13003
    @RequestMapping(value = "/data/get/month/twoYear", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object getTwoYear() {
        return CommonResponse.getSuccessResponse(registerService.getTwoYear());
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
        List<Sign> signs = new ArrayList<>();
        List<Sign> signList = registerService.getSignInfo();
        if (!CollectionUtils.isEmpty(signList)) {
            for (Sign sign : signList) {

            }
        }
        return CommonResponse.getSuccessResponse(signList);
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
                    long count = registerService.getTotalNumOfEvent(eventId, startDate, endDate, ignoreUnitIds);
                    map.put(eventId, count);
                }
            }
            return CommonResponse.getSuccessResponse(map);
        } else {
            String eventId = request.getEventId();
            return CommonResponse.getSuccessResponse(registerService.getTotalNumOfEvent(eventId, startDate, endDate, ignoreUnitIds));
        }
    }


    //获取团队信息
    @RequestMapping(value = "/team/find/all", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object DataFindAll() {
        return CommonResponse.getSuccessResponse(familyDoctorPersonnelService.selectFindAll());
    }


    //按机构取当月（预约挂号数量41001，总诊疗人次数41002，检查人次数41003，检验人次数41004，医院平均住院日41005）
    @RequestMapping(value = "/data/get/unit", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object getProcessedDataByUnit(AnalysisRequest request) {
        Date startDate = TimeUtil.FirstDayOfThisMonth();
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
                        Object item = registerService.countTotalNumByUnit(eventId, unitType, startDate, endDate, ignoreUnitIds);
                        if (item != null) {
                            map.put(eventId, item);
                        }
                    } else if (DataEvent.EVENT_TYPE_RATE == eventType) {
                        Object item = registerService.getAnalysisResultByUnit(eventId, unitType, startDate, endDate, ignoreUnitIds);
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
                    return CommonResponse.getSuccessResponse(registerService.countTotalNumByUnit(eventId, unitType, startDate, endDate, ignoreUnitIds));
                } else if (DataEvent.EVENT_TYPE_RATE == eventType) {
                    return CommonResponse.getSuccessResponse(registerService.getAnalysisResultByUnit(eventId, unitType, startDate, endDate, ignoreUnitIds));
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
                    Object item = byUnit ? registerService.getDataSetOfDay(eventId, unitType, startDate, endDate, ignoreUnitIds)
                            : registerService.getAnalysisResultByDay(eventId, unitType, startDate, endDate, ignoreUnitIds);
                    if (item != null) {
                        map.put(eventId, item);
                    }
                }
            }
            return CommonResponse.getSuccessResponse(map);
        } else {
            String eventId = request.getEventId();
            return CommonResponse.getSuccessResponse(registerService.getDataSetOfDay(eventId, startDate, endDate, ignoreUnitIds));
        }
    }



}


 
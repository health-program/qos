package com.paladin.qos.controller.address;


import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.analysis.DataConstantContainer;
import com.paladin.qos.analysis.DataConstantContainer.Event;
import com.paladin.qos.model.address.Address;
import com.paladin.qos.model.data.DataEvent;
import com.paladin.qos.service.address.AddressService;
import com.paladin.qos.service.address.vo.AddressVo;
import com.paladin.qos.service.analysis.AnalysisService;
import com.paladin.qos.service.analysis.data.DataCountUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/qos/address")
public class AddressController extends ControllerSupport {

    @Autowired
    private AddressService addressService;

    @Autowired
    private AnalysisService analysisService;

    @RequestMapping(value = "/search/all", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object searchAllAddress() {
        List<Address> addressList = new ArrayList<>();
        addressList=addressService.getAddress(null);
        return CommonResponse.getSuccessResponse(convertVo(addressList));
    }

    @RequestMapping(value = "/search/community", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object searchCommunity() {
        List<Address> addressList = new ArrayList<>();
        addressList=addressService.getAddress(2);
        return CommonResponse.getSuccessResponse(convertVo(addressList));
    }


    private List<AddressVo> convertVo(List<Address> addressList) {
        List<AddressVo> addressVoList = new ArrayList<>();
        for (Address address : addressList) {
            AddressVo addressVo = new AddressVo();
            addressVo = beanCopy(addressService.get(address.getUnitId()), addressVo);
            List<String> eventId1 = new ArrayList<>();
            eventId1.add("14001");
            eventId1.add("31004");
            mergeData(eventId1,addressVo,true);
            List<String> eventId2 = new ArrayList<>();
            eventId2.add("13001");
            eventId2.add("31008");
            mergeData(eventId2,addressVo,false);
            addressVoList.add(addressVo);
        }
        return addressVoList;
    }

    private void mergeData(List<String> eventIds,AddressVo addressVo,boolean isHospital){
        List<DataCountUnit> dataCountUnitList=new ArrayList<>();
        for (String eventId : eventIds) {
            Event event = DataConstantContainer.getEvent(eventId);
            if (event != null) {
                int unitType = getUnitType(event);
                dataCountUnitList = analysisService.countTotalNumByUnit(eventId, unitType,startDate(),endDate(),null);
                for(DataCountUnit dataCountUnit:dataCountUnitList){
                    if (StringUtils.equalsIgnoreCase(addressVo.getUnitId(),dataCountUnit.getUnitId())){
                        if(isHospital){
                            addressVo.setHospital(dataCountUnit.getCount());
                        }else{
                            addressVo.setPatients(dataCountUnit.getCount());
                        }

                    }
                }
            }
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

    private Date startDate(){
        // 获取当前月的第一天
        Calendar cale = Calendar.getInstance();
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        return  cale.getTime();
    }

    private Date endDate(){
        // 获取当前月的最后一天
        Calendar cale = Calendar.getInstance();
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        return  cale.getTime();
    }


}

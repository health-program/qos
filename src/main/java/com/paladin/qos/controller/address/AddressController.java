package com.paladin.qos.controller.address;


import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.controller.analysis.AnalysisRequest;
import com.paladin.qos.model.address.Address;
import com.paladin.qos.model.familydoctor.FamilyDoctorUnit;
import com.paladin.qos.service.address.AddressService;
import com.paladin.qos.service.address.vo.AddressVo;
import com.paladin.qos.service.analysis.data.DataCountUnit;
import com.paladin.qos.service.count.vo.CountReferralVO;
import com.paladin.qos.service.gongwei.vo.ArchivesManagementVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/qos/address")
public class AddressController extends ControllerSupport {

    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "/search/all", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object searchAllAddress() {
        List<Address> addressList = new ArrayList<>();
        List<AddressVo> addressVoList=new ArrayList<>();
        addressList=addressService.getAddress(null);
        for (Address address:addressList){
            AddressVo addressVo=new AddressVo();
            addressVo=beanCopy(addressService.get(address.getUnitId()), addressVo);
            addressVoList.add(addressVo);
        }
        return CommonResponse.getSuccessResponse(addressVoList);
    }

    @RequestMapping(value = "/search/community", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object searchCommunity() {
        List<Address> addressList = new ArrayList<>();
        List<AddressVo> addressVoList=new ArrayList<>();
        addressList=addressService.getAddress(2);
        for (Address address:addressList){
            AddressVo addressVo=new AddressVo();
            Address address1=addressService.get(address.getUnitId());
            addressVo=beanCopy(addressService.get(address.getUnitId()), addressVo);
            addressVoList.add(addressVo);
        }
        return CommonResponse.getSuccessResponse(addressVoList);
    }


}

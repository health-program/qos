package com.paladin.qos.service.address;

import com.paladin.framework.core.ServiceSupport;
import com.paladin.qos.mapper.address.AddressMapper;
import com.paladin.qos.model.address.Address;
import com.paladin.qos.model.count.CountReferral;
import com.paladin.qos.service.address.vo.AddressVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService extends ServiceSupport<Address> {


    @Autowired
    private AddressMapper addressMapper;

    public List<Address> getAddress(Integer type){
       return addressMapper.getAddressInfo(type);
    }

    public Address get(String id){
        return addressMapper.get(id);
    }
}

package com.paladin.qos.service.address;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paladin.qos.mapper.address.AddressMapper;
import com.paladin.qos.model.address.Address;

@Service
public class AddressService {


    @Autowired
    private AddressMapper addressMapper;

    public List<Address> getAddress(Integer type){
       return addressMapper.getAddressInfo(type);
    }

    public Address get(String id){
        return addressMapper.get(id);
    }
}

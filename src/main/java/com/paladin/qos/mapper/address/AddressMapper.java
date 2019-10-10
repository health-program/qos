package com.paladin.qos.mapper.address;

import com.paladin.qos.model.address.Address;
import com.paladin.qos.service.address.vo.AddressVo;
import com.paladin.qos.service.analysis.data.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddressMapper {

        List<Address> getAddressInfo(@Param("type") Integer type);
        Address get(@Param("id") String  id);
}

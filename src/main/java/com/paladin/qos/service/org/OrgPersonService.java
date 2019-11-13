package com.paladin.qos.service.org;

import com.paladin.framework.core.ServiceSupport;
import com.paladin.qos.mapper.org.OrgPersonMapper;
import com.paladin.qos.model.org.OrgPerson;
import com.paladin.qos.service.org.vo.OrgPersonReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrgPersonService extends ServiceSupport<OrgPerson> {


    @Autowired
    private OrgPersonMapper orgPersonMapper;

    public  OrgPersonReport getTotalNumber(){
        return orgPersonMapper.getTotalNumber();
    }

}
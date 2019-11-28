package com.paladin.qos.mapper.org;

import com.paladin.framework.core.configuration.mybatis.CustomMapper;
import com.paladin.qos.model.org.OrgPerson;
import com.paladin.qos.model.org.OrgUser;
import com.paladin.qos.service.org.vo.OrgPersonReport;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;


public interface OrgPersonMapper extends CustomMapper<OrgPerson>{

    OrgPersonReport getTotalNumber();

}
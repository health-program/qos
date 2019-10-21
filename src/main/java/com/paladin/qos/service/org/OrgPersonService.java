package com.paladin.qos.service.org;

import com.paladin.common.model.org.OrgRole;
import com.paladin.common.model.syst.SysUser;
import com.paladin.common.service.org.OrgRoleService;
import com.paladin.common.service.syst.SysUserService;
import com.paladin.framework.common.Condition;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.common.QueryType;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.framework.core.copy.SimpleBeanCopier.SimpleBeanCopyUtil;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.qos.analysis.DataConstantContainer;
import com.paladin.qos.analysis.DataConstantContainer.Unit;
import com.paladin.qos.core.QosUserSession;
import com.paladin.qos.mapper.org.OrgPersonMapper;
import com.paladin.qos.model.org.OrgPerson;
import com.paladin.qos.model.org.OrgUser;
import com.paladin.qos.service.org.dto.OrgUserDTO;
import com.paladin.qos.service.org.dto.OrgUserQuery;
import com.paladin.qos.service.org.vo.OrgPersonReport;
import com.paladin.qos.service.org.vo.OrgUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class OrgPersonService extends ServiceSupport<OrgPerson> {


    @Autowired
    private OrgPersonMapper orgPersonMapper;

    public  OrgPersonReport getTotalNumber(){
        return orgPersonMapper.getTotalNumber();
    }

}
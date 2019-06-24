package com.paladin.qos.service.familydoctor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.util.StringUtil;
import com.paladin.qos.model.familydoctor.FamilyDoctorPersonnel;
import com.paladin.qos.model.familydoctor.FamilyDoctorTeam;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.framework.core.exception.BusinessException;

@Service
public class FamilyDoctorTeamService extends ServiceSupport<FamilyDoctorTeam> {

    @Autowired
    private FamilyDoctorPersonnelService familyDoctorPersonnelService;
    
    
    public int removeTeam(String id) {
	if (StringUtil.isEmpty(id)) {
	    throw new BusinessException("该团队不存在");
	}
	List<FamilyDoctorPersonnel> list = familyDoctorPersonnelService.findPersonnel(id);
	if (list.size() > 0) {
	    throw new BusinessException("请先删除团队下属的人员");
	}
	return removeByPrimaryKey(id);
    }
}
package com.paladin.qos.service.familydoctor;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.qos.model.familydoctor.FamilyDoctorPersonnel;
import com.paladin.qos.service.familydoctor.dto.FamilyDoctorPersonnelQuery;
import com.paladin.qos.service.familydoctor.vo.FamilyDoctorPersonnelVO;
import com.paladin.framework.common.Condition;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.common.QueryType;
import com.paladin.framework.core.ServiceSupport;

@Service
public class FamilyDoctorPersonnelService extends ServiceSupport<FamilyDoctorPersonnel> {

    public List<FamilyDoctorPersonnel> findPersonnel(String teamId) {
   	return searchAll(new Condition(FamilyDoctorPersonnel.COLUMN_TEAM_ID,QueryType.EQUAL, teamId));
       }
    
    public PageResult<FamilyDoctorPersonnelVO> personnelQuery(FamilyDoctorPersonnelQuery query){
	if(query == null){
	  
	}
	Page<FamilyDoctorPersonnelVO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
	searchPage(query);
	return new PageResult<>(page);
    }
}
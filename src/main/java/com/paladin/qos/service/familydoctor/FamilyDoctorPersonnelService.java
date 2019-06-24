package com.paladin.qos.service.familydoctor;

import java.util.List;

import org.springframework.stereotype.Service;
import com.paladin.qos.model.familydoctor.FamilyDoctorPersonnel;
import com.paladin.framework.common.Condition;
import com.paladin.framework.common.QueryType;
import com.paladin.framework.core.ServiceSupport;

@Service
public class FamilyDoctorPersonnelService extends ServiceSupport<FamilyDoctorPersonnel> {

    public List<FamilyDoctorPersonnel> findPersonnel(String teamId) {
   	return searchAll(new Condition(FamilyDoctorPersonnel.COLUMN_TEAM_ID,QueryType.EQUAL, teamId));
       }
}
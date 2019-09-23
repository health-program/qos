package com.paladin.qos.mapper.familydoctor;

import com.paladin.qos.model.familydoctor.FamilyDoctorUnit;
import com.paladin.framework.core.configuration.mybatis.CustomMapper;

public interface FamilyDoctorUnitMapper extends CustomMapper<FamilyDoctorUnit>{

    FamilyDoctorUnit getFamilyUnit();
}
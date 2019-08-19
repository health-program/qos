package com.paladin.qos.mapper.familydoctor;


import io.lettuce.core.dynamic.annotation.Param;

import com.paladin.qos.model.familydoctor.FamilyDoctorPersonnel;
import com.paladin.framework.core.configuration.mybatis.CustomMapper;

public interface FamilyDoctorPersonnelMapper extends CustomMapper<FamilyDoctorPersonnel>{

    public int countPersonnel(@Param("name")String name);
    
}
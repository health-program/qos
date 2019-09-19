package com.paladin.qos.mapper.familydoctor;


import java.util.List;

import io.lettuce.core.dynamic.annotation.Param;

import com.paladin.qos.model.familydoctor.FamilyDoctorPersonnel;
import com.paladin.qos.service.familydoctor.vo.DataFamilyDoctorTeamVo;
import com.paladin.framework.core.configuration.mybatis.CustomMapper;

public interface FamilyDoctorPersonnelMapper extends CustomMapper<FamilyDoctorPersonnel>{

    public int countPersonnel(@Param("name")String name);
    
    public List<DataFamilyDoctorTeamVo> personnelNum();
    
}
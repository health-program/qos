package com.paladin.qos.mapper.familydoctor;

import java.util.List;

import io.lettuce.core.dynamic.annotation.Param;

import com.paladin.qos.model.familydoctor.FamilyDoctorTeam;
import com.paladin.qos.service.familydoctor.vo.DataFamilyDoctorTeamVo;
import com.paladin.qos.service.familydoctor.vo.FamilyDoctorTeamVO;
import com.paladin.framework.core.configuration.mybatis.CustomMapper;

public interface FamilyDoctorTeamMapper extends CustomMapper<FamilyDoctorTeam>{

    public FamilyDoctorTeamVO getTeam(@Param("teamName")String teamName);
    
    public List<DataFamilyDoctorTeamVo> teamNum();
    
    public int DataTeamCount();
}
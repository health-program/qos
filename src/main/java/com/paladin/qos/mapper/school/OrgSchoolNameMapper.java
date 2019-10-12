package com.paladin.qos.mapper.school;

import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

import com.paladin.qos.model.school.OrgSchoolName;
import com.paladin.qos.service.school.vo.OrgSchoolIdNameVO;
import com.paladin.qos.service.school.vo.SchoolNameVO;
import com.paladin.framework.core.configuration.mybatis.CustomMapper;

public interface OrgSchoolNameMapper extends CustomMapper<OrgSchoolName>{

    public List<SchoolNameVO> schoolNameFind();
    
    public SchoolNameVO getSchoolName(@Param("schoolName") String schoolName);

	public List<OrgSchoolIdNameVO> schoolIdNameFind();
}
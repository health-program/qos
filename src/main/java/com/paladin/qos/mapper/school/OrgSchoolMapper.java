package com.paladin.qos.mapper.school;

import java.util.List;

import com.paladin.qos.model.school.OrgSchool;
import com.paladin.qos.service.school.dto.OrgSchoolQuery;
import com.paladin.qos.service.school.vo.OrgSchoolVO;
import com.paladin.framework.core.configuration.mybatis.CustomMapper;

public interface OrgSchoolMapper extends CustomMapper<OrgSchool>{

    public List<OrgSchoolVO> findSchool(OrgSchoolQuery query);
}
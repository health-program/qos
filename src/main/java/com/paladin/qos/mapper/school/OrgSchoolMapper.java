package com.paladin.qos.mapper.school;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.paladin.framework.core.configuration.mybatis.CustomMapper;
import com.paladin.qos.model.school.OrgSchool;
import com.paladin.qos.service.school.dto.OrgSchoolCountsQuery;
import com.paladin.qos.service.school.dto.OrgSchoolQuery;
import com.paladin.qos.service.school.vo.OrgSchoolCountsGroupByNatureVO;
import com.paladin.qos.service.school.vo.OrgSchoolDoctorCountVO;
import com.paladin.qos.service.school.vo.OrgSchoolPersonnelCountVO;
import com.paladin.qos.service.school.vo.OrgSchoolVO;

public interface OrgSchoolMapper extends CustomMapper<OrgSchool> {

    public List<OrgSchoolVO> findSchool(OrgSchoolQuery query);

    public List<OrgSchoolPersonnelCountVO> schoolPersonnelCount(
	    OrgSchoolQuery query);

    public List<OrgSchoolDoctorCountVO> schoolDoctorCount(OrgSchoolQuery query);

    public OrgSchoolVO parentSchoolId(Map<String, Object> params);

    public List<OrgSchoolCountsGroupByNatureVO> schoolCountsGroupByNature(OrgSchoolCountsQuery query);

    public List<OrgSchoolCountsGroupByNatureVO> schoolCountsGroupByAffiliation(OrgSchoolCountsQuery query);

    public List<OrgSchoolCountsGroupByNatureVO> schoolPeopleCountsGroupByNature(OrgSchoolCountsQuery query);

    public List<OrgSchoolCountsGroupByNatureVO> schoolPeopleCountsGroupByAffiliation(OrgSchoolCountsQuery query);

    public void updateSchoolTotal(String orgSchoolId);
    
    public List<OrgSchool> orgSchoolList(@Param("agencyId") String[] agencyId);
}
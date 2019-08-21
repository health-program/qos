package com.paladin.qos.mapper.count;

import com.paladin.framework.core.configuration.mybatis.CustomMapper;
import com.paladin.qos.model.count.CountReferral;
import com.paladin.qos.model.school.OrgSchool;
import com.paladin.qos.service.count.dto.CountReferralQuery;
import com.paladin.qos.service.count.vo.CountReferralVO;
import com.paladin.qos.service.infectioncomplication.dto.InfectionQuery;
import com.paladin.qos.service.infectioncomplication.vo.InfectionVO;
import com.paladin.qos.service.school.dto.OrgSchoolQuery;
import com.paladin.qos.service.school.vo.OrgSchoolDoctorCountVO;
import com.paladin.qos.service.school.vo.OrgSchoolPersonnelCountVO;
import com.paladin.qos.service.school.vo.OrgSchoolVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CountReferralMapper extends CustomMapper<CountReferral>{


    //根据查询条件搜索记录列表
    List<CountReferralVO> findReferralRecord(CountReferralQuery query);
}
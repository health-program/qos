package com.paladin.qos.mapper.epidemic;



import java.util.List;

import com.paladin.framework.core.configuration.mybatis.CustomMapper;
import com.paladin.qos.model.epidemic.EpidemicSituation;
import com.paladin.qos.service.epidemic.dto.EpidemicSituationQueryDTO;
import com.paladin.qos.service.epidemic.vo.DataEpidemicSituationVO;
import com.paladin.qos.service.epidemic.vo.EpidemicSituationVO;
import com.paladin.qos.service.school.dto.OrgSchoolCountsQuery;
import com.paladin.qos.service.school.vo.OrgSchoolCountsGroupByNatureVO;
import com.paladin.qos.service.school.vo.OrgSchoolEpidemicRateVO;


public interface EpidemicSituationMapper extends CustomMapper<EpidemicSituation>{
    
    public List<EpidemicSituationVO> searchFindPage(EpidemicSituationQueryDTO query);

	public List<OrgSchoolCountsGroupByNatureVO> epidemicCountsGroupByUnit(
			OrgSchoolCountsQuery query);

	public List<OrgSchoolCountsGroupByNatureVO> epidemicPeopleCountsGroupByUnit(
			OrgSchoolCountsQuery query);
    
    public List<DataEpidemicSituationVO> dataTraceabilityRate(EpidemicSituationQueryDTO query);

	public List<OrgSchoolEpidemicRateVO> queryEpidemicRatesByAffiliation(
			OrgSchoolCountsQuery query);

	public List<OrgSchoolEpidemicRateVO> queryEpidemicRatesByNature(
			OrgSchoolCountsQuery query);
}
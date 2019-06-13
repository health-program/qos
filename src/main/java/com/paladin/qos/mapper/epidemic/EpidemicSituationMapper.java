package com.paladin.qos.mapper.epidemic;



import java.util.List;

import com.paladin.framework.core.configuration.mybatis.CustomMapper;
import com.paladin.qos.model.epidemic.EpidemicSituation;
import com.paladin.qos.service.epidemic.dto.EpidemicSituationQueryDTO;
import com.paladin.qos.service.epidemic.vo.EpidemicSituationVO;


public interface EpidemicSituationMapper extends CustomMapper<EpidemicSituation>{
    
    public List<EpidemicSituationVO> searchFindPage(EpidemicSituationQueryDTO query);
    
}
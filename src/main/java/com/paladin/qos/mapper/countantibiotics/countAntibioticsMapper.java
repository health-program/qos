package com.paladin.qos.mapper.countantibiotics;

import java.util.List;

import com.paladin.framework.core.configuration.mybatis.CustomMapper;
import com.paladin.qos.model.countantibiotics.CountAntibiotics;
import com.paladin.qos.service.countantibiotics.dto.CountAntibioticsDTO;
import com.paladin.qos.service.countantibiotics.vo.CountAntibioticsVO;
import com.paladin.qos.service.infectionAndComplication.dto.OperationComplicationQueryDTO;
import com.paladin.qos.service.infectionAndComplication.vo.OperationComplicationVO;


public interface countAntibioticsMapper extends CustomMapper<CountAntibiotics> {
	
	
  public List<CountAntibioticsVO> selecttoAll(CountAntibioticsDTO query);

 

    public Object queryById(String id);

   
}

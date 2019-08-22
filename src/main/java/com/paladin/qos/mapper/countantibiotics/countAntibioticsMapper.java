package com.paladin.qos.mapper.countantibiotics;

import com.paladin.framework.core.configuration.mybatis.CustomMapper;
import com.paladin.qos.model.countantibiotics.CountAntibiotics;
import com.paladin.qos.service.countantibiotics.dto.CountAntibioticsDTO;
import com.paladin.qos.service.countantibiotics.vo.CountAntibioticsVO;

import java.util.List;


public interface countAntibioticsMapper extends CustomMapper<CountAntibiotics> {
	
	
  public List<CountAntibioticsVO> selecttoAll(CountAntibioticsDTO query);

 

  public Object queryById(String id);

   
}

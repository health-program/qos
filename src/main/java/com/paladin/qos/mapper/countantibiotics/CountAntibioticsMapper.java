package com.paladin.qos.mapper.countantibiotics;

import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

import com.paladin.framework.core.configuration.mybatis.CustomMapper;
import com.paladin.qos.model.countantibiotics.CountAntibiotics;
import com.paladin.qos.service.countantibiotics.dto.CountAntibioticsDTO;
import com.paladin.qos.service.countantibiotics.vo.CountAntibioticsVO;

public interface CountAntibioticsMapper extends CustomMapper<CountAntibiotics> {

	public List<CountAntibioticsVO> selecttoAll(CountAntibioticsDTO query);

	public Object queryById(String id);
	
	public int judge(@Param("unitId") String unitId);

}

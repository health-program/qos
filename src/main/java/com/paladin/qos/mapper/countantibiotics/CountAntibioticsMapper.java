package com.paladin.qos.mapper.countantibiotics;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.paladin.framework.core.configuration.mybatis.CustomMapper;
import com.paladin.qos.model.countantibiotics.CountAntibiotics;
import com.paladin.qos.service.countantibiotics.dto.CountAntibioticsQuery;
import com.paladin.qos.service.countantibiotics.vo.CountAntibioticsVO;

@Service
public interface CountAntibioticsMapper extends CustomMapper<CountAntibiotics> {

	public List<CountAntibioticsVO> selecttoAll(CountAntibioticsQuery query);

	public int judge(@Param("unitId") String unitId);

	public Object queryById(String id);

	public List<CountAntibioticsVO> getReportByQuery(@Param("unitId") String unitId, @Param("month") Date month);

}

package com.paladin.qos.analysis.impl.yiyuan;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paladin.data.dynamic.SqlSessionContainer;
import com.paladin.qos.dynamic.mapper.yiyuan.RehospitalzationAnalysisMyocardialInfarctionMapper;

/**
 * 急性心肌梗死再住院率统计
 * 
 * @author FM
 *
 */
@Component
public class RehospitalzationAnalysisMyocardialInfarction extends YiyuanDataProcessor {

	private RehospitalzationAnalysisMyocardialInfarctionMapper mapper;
	@Autowired
	private SqlSessionContainer sqlSessionContainer;

	public RehospitalzationAnalysisMyocardialInfarctionMapper getMapper() {
		if (mapper == null) {
			mapper = sqlSessionContainer.getMapper(RehospitalzationAnalysisMyocardialInfarctionMapper.class);
		}
		return mapper;
	}

	public static final String EVENT_ID = "11101";

	@Override
	public String getEventId() {
		return EVENT_ID;
	}

	@Override
	public long getTotalNum(Date startTime, Date endTime, String unitId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("startTime", startTime);
		params.put("endTime", endTime);
		params.put("unitId", unitId);
		sqlSessionContainer.setCurrentDataSource(getDataSourceByUnit(unitId));
		return getMapper().getTotalNum(params);
	}

	@Override
	public long getEventNum(Date startTime, Date endTime, String unitId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("startTime", startTime);
		params.put("endTime", endTime);
		params.put("unitId", unitId);
		sqlSessionContainer.setCurrentDataSource(getDataSourceByUnit(unitId));
		return getMapper().getEventNum(params);
	}

}

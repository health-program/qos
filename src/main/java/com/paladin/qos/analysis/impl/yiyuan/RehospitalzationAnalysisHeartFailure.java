package com.paladin.qos.analysis.impl.yiyuan;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paladin.data.dynamic.SqlSessionContainer;
import com.paladin.qos.analysis.impl.yiyuan.YiyuanDataProcessor;
import com.paladin.qos.dynamic.mapper.yiyuan.RehospitalzationAnalysisGoiterMapper;
import com.paladin.qos.dynamic.mapper.yiyuan.RehospitalzationAnalysisHeartFailureMapper;

/**
 * 充血性心力衰竭再住院率统计
 */
@Component
public class RehospitalzationAnalysisHeartFailure extends YiyuanDataProcessor {
	
	private RehospitalzationAnalysisHeartFailureMapper mapper;
	@Autowired
	private SqlSessionContainer sqlSessionContainer;

	public RehospitalzationAnalysisHeartFailureMapper getMapper() {
		if (mapper == null) {
			mapper = sqlSessionContainer
					.getMapper(RehospitalzationAnalysisHeartFailureMapper.class);
		}
		return mapper;
	}
	public static final String EVENT_ID = "11102";

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

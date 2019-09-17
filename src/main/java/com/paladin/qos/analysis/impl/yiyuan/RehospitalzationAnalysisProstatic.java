package com.paladin.qos.analysis.impl.yiyuan;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paladin.data.dynamic.SqlSessionContainer;
import com.paladin.qos.dynamic.mapper.yiyuan.RehospitalzationAnalysisProstaticMapper;

/**
 * 前列腺增生再住院率统计
 * 
 * @author FM
 *
 */
@Component
public class RehospitalzationAnalysisProstatic extends YiyuanDataProcessor {

	private RehospitalzationAnalysisProstaticMapper mapper;
	@Autowired
	private SqlSessionContainer sqlSessionContainer;

	public RehospitalzationAnalysisProstaticMapper getMapper() {
		if (mapper == null) {
			mapper = sqlSessionContainer.getMapper(RehospitalzationAnalysisProstaticMapper.class);
		}
		return mapper;
	}

	public static final String EVENT_ID = "11112";

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

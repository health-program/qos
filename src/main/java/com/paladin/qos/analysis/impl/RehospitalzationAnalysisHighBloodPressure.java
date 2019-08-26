package com.paladin.qos.analysis.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paladin.qos.analysis.DataProcessor;
import com.paladin.qos.mapper.analysis.RehospitalzationAnalysisHighBloodPressureMapper;

/**
 * 高血压病（成人）再住院率统计
 * @author FM
 *
 */
@Component
public class RehospitalzationAnalysisHighBloodPressure extends DataProcessor{
	@Autowired 
	private RehospitalzationAnalysisHighBloodPressureMapper rehospitalzationAnalysisHighBloodPressureMapper;
	
	public static final String EVENT_ID = "11115";
	
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
		return rehospitalzationAnalysisHighBloodPressureMapper.getTotalNum(params);
	}

	@Override
	public long getEventNum(Date startTime, Date endTime, String unitId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("startTime", startTime);
		params.put("endTime", endTime);
		params.put("unitId", unitId);
		return rehospitalzationAnalysisHighBloodPressureMapper.getEventNum(params);
	}
}

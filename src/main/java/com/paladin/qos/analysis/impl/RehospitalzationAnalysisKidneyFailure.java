package com.paladin.qos.analysis.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paladin.qos.analysis.DataProcessor;
import com.paladin.qos.mapper.analysis.RehospitalzationAnalysisKidneyFailureMapper;

/**
 * 肾衰竭再住院率统计
 * @author FM
 *
 */
@Component
public class RehospitalzationAnalysisKidneyFailure extends DataProcessor{
	
	@Autowired 
	private RehospitalzationAnalysisKidneyFailureMapper rehospitalzationAnalysisKidneyFailureMapper;
	
	public static final String EVENT_ID = "11113";
	
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
		return rehospitalzationAnalysisKidneyFailureMapper.getTotalNum(params);
	}

	@Override
	public long getEventNum(Date startTime, Date endTime, String unitId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("startTime", startTime);
		params.put("endTime", endTime);
		params.put("unitId", unitId);
		return rehospitalzationAnalysisKidneyFailureMapper.getEventNum(params);
	}
}

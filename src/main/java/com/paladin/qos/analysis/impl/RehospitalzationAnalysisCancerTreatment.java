package com.paladin.qos.analysis.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paladin.qos.analysis.DataProcessor;
import com.paladin.qos.mapper.analysis.RehospitalzationAnalysisCancerTreatmentMapper;

/**
 * 恶性肿瘤维持性化学治疗再住院率统计
 * @author FM
 *
 */
@Component
public class RehospitalzationAnalysisCancerTreatment extends DataProcessor{
	@Autowired 
	private RehospitalzationAnalysisCancerTreatmentMapper rehospitalzationAnalysisCancerTreatmentMapper;
	
	public static final String EVENT_ID = "11118";
	
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
		return rehospitalzationAnalysisCancerTreatmentMapper.getTotalNum(params);
	}

	@Override
	public long getEventNum(Date startTime, Date endTime, String unitId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("startTime", startTime);
		params.put("endTime", endTime);
		params.put("unitId", unitId);
		return rehospitalzationAnalysisCancerTreatmentMapper.getEventNum(params);
	}
}

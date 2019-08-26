package com.paladin.qos.analysis.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paladin.qos.analysis.DataProcessor;
import com.paladin.qos.mapper.analysis.RehospitalzationAnalysisHeartFailureMapper;
import com.paladin.qos.mapper.analysis.RehospitalzationAnalysisPneumoniaMapper;

/**
 * 细菌性肺炎（成人、无并发症）再住院率统计
 * @author FM
 *
 */
@Component
public class RehospitalzationAnalysisPneumonia extends DataProcessor{
	@Autowired 
	private RehospitalzationAnalysisPneumoniaMapper rehospitalzationAnalysisPneumoniaMapper;
	
	public static final String EVENT_ID = "11107";
	
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	@Override
	public String getEventId() {

		return EVENT_ID;
	}

	@Override
	public long getTotalNum(Date startTime, Date endTime, String unitId) {

		Map<String, Object> params = new HashMap<String, Object>();
		String aa  = format.format(startTime);
		params.put("startTime", format.format(startTime));
		params.put("endTime", format.format(endTime));
		params.put("unitId", unitId);
		return rehospitalzationAnalysisPneumoniaMapper.getTotalNum(params);
	}

	@Override
	public long getEventNum(Date startTime, Date endTime, String unitId) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("startTime", format.format(startTime));
		params.put("endTime", format.format(endTime));
		params.put("unitId", unitId);
		return rehospitalzationAnalysisPneumoniaMapper.getEventNum(params);
	}
}

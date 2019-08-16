package com.paladin.qos.analysis.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paladin.qos.analysis.DataProcessor;
import com.paladin.qos.mapper.analysis.RehospitalizationAnalysisMapper;
@Component
public class RehospitalzationAnalysisDataProcessor extends DataProcessor{
	
	@Autowired 
	private RehospitalizationAnalysisMapper rehospitalizationAnalysisMapper;
	
    
	public static final String EVENT_ID = "11001";
	
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	@Override
	public String getEventId() {

		return EVENT_ID;
	}

	/**
	 * 同期出院患者总人次（除死亡患者外）
	 */
	@Override
	public long getTotalNum(Date startTime, Date endTime, String unitId) {

		Map<String, Object> params = new HashMap<String, Object>();
		String aa  = format.format(startTime);
		params.put("startTime", format.format(startTime));
		params.put("endTime", format.format(endTime));
		params.put("unitId", unitId);
		return rehospitalizationAnalysisMapper.getTotalNum(params);
	}

	/**
	 * 出院31天再住院患者人次
	 */
	@Override
	public long getEventNum(Date startTime, Date endTime, String unitId) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("startTime", format.format(startTime));
		params.put("endTime", format.format(endTime));
		params.put("unitId", unitId);
		return rehospitalizationAnalysisMapper.getEventNum(params);
	}

}

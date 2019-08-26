package com.paladin.qos.analysis.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paladin.qos.analysis.DataProcessor;
import com.paladin.qos.mapper.analysis.KeyOperationPercutaneousCoronaryInterventionMapper;

/**
 * 经皮冠状动脉介入治疗死亡人率
 * 
 * @author FM
 *
 */
@Component
public class KeyOperationPercutaneousCoronaryIntervention extends DataProcessor {
	@Autowired
	private KeyOperationPercutaneousCoronaryInterventionMapper keyOperationPercutaneousCoronaryInterventionMapper;

	public static final String EVENT_ID = "12101";

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
		return keyOperationPercutaneousCoronaryInterventionMapper.getTotalNum(params);
	}

	@Override
	public long getEventNum(Date startTime, Date endTime, String unitId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("startTime", startTime);
		params.put("endTime", endTime);
		params.put("unitId", unitId);
		return keyOperationPercutaneousCoronaryInterventionMapper.getEventNum(params);
	}
}
